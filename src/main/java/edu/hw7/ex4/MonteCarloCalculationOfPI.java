package edu.hw7.ex4;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MonteCarloCalculationOfPI {

    public static final int SUB_TASKS_AMOUNT = 50;
    private static final double PI_MULTIPLIER = 4;

    public static double calculatePiNotParallel(int iterations) {
        int circleCount = 0;
        double x;
        double y;
        Random random = new Random();
        for (int i = 0; i < iterations; i++) {
            x = random.nextDouble();
            y = random.nextDouble();
            if (isInsideCircle(x, y)) {
                circleCount++;
            }
        }
        return PI_MULTIPLIER * circleCount / iterations;
    }

    public static double calculatePiParallel(long iterations) {
        long iterationsPerThread = iterations / SUB_TASKS_AMOUNT;
        long realIterations = iterations - iterations % SUB_TASKS_AMOUNT;
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 0; i < SUB_TASKS_AMOUNT; i++) {
                executorService.execute(new PiWorker(iterationsPerThread));
            }
            executorService.shutdown();
        }
        return PI_MULTIPLIER * PiWorker.getAndClear() / realIterations;
    }

    public static boolean isInsideCircle(double x, double y) {
        return x * x + y * y < 1;
    }

    class PiWorker implements Runnable {

        private static final AtomicLong POINTS_IN_CIRCLE = new AtomicLong();

        private final long numIterations;

        PiWorker(long numIterations) {
            this.numIterations = numIterations;
        }

        public static long getAndClear() {
            long pointsInCircle = POINTS_IN_CIRCLE.get();
            POINTS_IN_CIRCLE.set(0);
            return pointsInCircle;
        }

        @Override
        public void run() {
            int circleCounter = 0;
            var random = ThreadLocalRandom.current();
            for (int i = 0; i < numIterations; i++) {
                double x = random.nextDouble();
                double y = random.nextDouble();
                if (isInsideCircle(x, y)) {
                    circleCounter++;
                }
            }
            POINTS_IN_CIRCLE.addAndGet(circleCounter);
        }
    }
}
