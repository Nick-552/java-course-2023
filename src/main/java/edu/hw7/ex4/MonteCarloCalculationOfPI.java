package edu.hw7.ex4;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MonteCarloCalculationOfPI {

    public static final int SUB_TASKS_AMOUNT = 70;
    private static final double PI_MULTIPLIER = 4;

    public static double calculatePiNotParallel(int iterations) {
        int circleCount = 0;
        double x;
        double y;
        Random random = ThreadLocalRandom.current();
        for (int i = 0; i < iterations; i++) {
            x = random.nextDouble();
            y = random.nextDouble();
            if (isInsideCircle(x, y)) {
                circleCount++;
            }
        }
        return PI_MULTIPLIER * circleCount / iterations;
    }

    @SneakyThrows
    public static double calculatePiParallel(long iterations) {
        long iterationsPerThread = iterations / SUB_TASKS_AMOUNT;
        long realIterations = iterations - iterations % SUB_TASKS_AMOUNT;
        long totalInCircle = 0;
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            Future<Long>[] futures = new Future[SUB_TASKS_AMOUNT];
            for (int i = 0; i < SUB_TASKS_AMOUNT; i++) {
                futures[i] = executorService.submit(new PiWorker(iterationsPerThread));
            }
            executorService.shutdown();
            executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
            for (int i = 0; i < SUB_TASKS_AMOUNT; i++) {
                totalInCircle += futures[i].get();
            }
        }
        return PI_MULTIPLIER * totalInCircle / realIterations;
    }

    public static boolean isInsideCircle(double x, double y) {
        return x * x + y * y < 1;
    }

    class PiWorker implements Callable<Long> {

        private final long numIterations;

        PiWorker(long numIterations) {
            this.numIterations = numIterations;
        }

        @Override
        public Long call() {
            long circleCounter = 0;
            var random = ThreadLocalRandom.current();
            for (int i = 0; i < numIterations; i++) {
                double x = random.nextDouble();
                double y = random.nextDouble();
                if (isInsideCircle(x, y)) {
                    circleCounter++;
                }
            }
            return circleCounter;
        }
    }
}
