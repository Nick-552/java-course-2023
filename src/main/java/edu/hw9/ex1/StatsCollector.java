package edu.hw9.ex1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StatsCollector {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    private final List<Future<Metric>> futuresMetrics = new CopyOnWriteArrayList<>();

    public void push(String metricName, double... values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("values must not be both empty or null");
        }
        if (EXECUTOR_SERVICE.isShutdown()) {
            throw new IllegalStateException("Executor service is shutdowned");
        }
        futuresMetrics.add(EXECUTOR_SERVICE.submit(() -> collectStats(metricName, values)));
    }

    public List<Metric> stats() {
        return futuresMetrics.stream()
            .map(metricFuture -> {
                try {
                    return metricFuture.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            })
            .toList();
    }

    private static Metric collectStats(String metricName, double... values) {
        return new Metric(
            metricName,
            Arrays.stream(values).sum(),
            Arrays.stream(values).average().orElseThrow(),
            Arrays.stream(values).min().orElseThrow(),
            Arrays.stream(values).max().orElseThrow()
        );
    }
}
