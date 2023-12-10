package edu.project4.renderer;

import edu.project4.model.FractalImage;
import edu.project4.model.Rectangle;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.affine.AffineTransformation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class ParallelRenderer extends AbstractRenderer {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors()
    );

    public ParallelRenderer(int width, int height, List<Transformation> variations) {
        super(width, height, variations);
    }

    @SneakyThrows
    @Override
    protected void processSamples(
        FractalImage fractalImage,
        Rectangle rectangle,
        List<AffineTransformation> affineTransformations,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        List<Future<Void>> futures = new ArrayList<>();
        for (int i = 0; i < samples; i++) {
            futures.add(EXECUTOR_SERVICE.submit(
                () -> {
                    processSample(fractalImage, rectangle, affineTransformations, iterPerSample, symmetry);
                    return null;
                }
            ));
        }
        for (var future : futures) {
            future.get();
        }
    }

    @Override
    public void finish() {
        EXECUTOR_SERVICE.close();
    }
}
