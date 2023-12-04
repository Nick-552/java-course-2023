package edu.hw8.ex2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public final class FixedThreadPool implements ThreadPool {

    private final ThreadPoolWorker[] threads;

    private final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();

    private final AtomicBoolean isShutdowned = new AtomicBoolean(false);

    private FixedThreadPool(int nThreads) {
        threads = new ThreadPoolWorker[nThreads];
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new ThreadPoolWorker();
        }
        start();
    }

    public static FixedThreadPool create(int nThreads) {
        return new FixedThreadPool(nThreads);
    }

    @Override
    public void start() {
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
    public void shutdown() {
        isShutdowned.set(true);
    }

    @Override
    public void close() throws Exception {
        shutdown();
        tasks.clear();
        for (var thread : threads) {
            thread.interrupt();
        }
        for (var thread : threads) {
            thread.join();
        }
    }

    public class ThreadPoolWorker extends Thread {
        @Override
        public void run() {
            while (!isShutdowned.get() || !tasks.isEmpty()) {
                try {
                    Runnable task = tasks.take();
                    task.run();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
