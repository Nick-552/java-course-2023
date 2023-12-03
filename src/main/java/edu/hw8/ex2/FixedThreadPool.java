package edu.hw8.ex2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public final class FixedThreadPool implements ThreadPool {

    private final ThreadPoolWorker[] threads;

    private final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();

    private final AtomicBoolean isShutdowned = new AtomicBoolean(true);

    private FixedThreadPool(int nThreads) {
        threads = new ThreadPoolWorker[nThreads];
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new ThreadPoolWorker();
        }
    }

    public static FixedThreadPool create(int nThreads) {
        return new FixedThreadPool(nThreads);
    }

    @Override
    public void start() {
        isShutdowned.set(false);
        for (var thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (!isShutdowned.get()) {
            tasks.add(runnable);
        }
    }

    @Override
    public void close() throws Exception {
        isShutdowned.set(true);
    }

    public class ThreadPoolWorker extends Thread {
        @Override
        public void run() {
            while (!isShutdowned.get() || !tasks.isEmpty()) {
                Runnable task = tasks.poll();
                if (task != null) {
                    task.run();
                }
            }
        }
    }
}
