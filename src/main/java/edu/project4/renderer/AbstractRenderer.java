package edu.project4.renderer;

import edu.project4.imageProcessor.ColorCorrection;
import edu.project4.model.AffineTransformationCoefficients;
import edu.project4.model.Color;
import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import edu.project4.model.Rectangle;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.affine.AffineTransformation;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import static edu.project4.utils.ListUtils.getRandomElement;
import static java.lang.Math.PI;

@Log4j2()
@RequiredArgsConstructor
public abstract class AbstractRenderer implements Renderer {

    private static final int START = -20;

    private final int width;

    private final int height;

    private final List<Transformation> variations;

    @Override
    public FractalImage render(
        Rectangle world,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        FractalImage fractalImage = FractalImage.create(width, height);
        var affineTransformations = createAffineTransformations(samples);
        log.info("started");
        processSamples(fractalImage, world, affineTransformations, samples, iterPerSample, symmetry);
        log.info("rendered");
        new ColorCorrection(2.2).process(fractalImage);
        log.info("gamma corrected");
        return fractalImage;
    }

    protected abstract void processSamples(
        FractalImage fractalImage,
        Rectangle rectangle,
        List<AffineTransformation> affineTransformations,
        int samples,
        int iterPerSample,
        int symmetry
    );

    protected void processSample(
        FractalImage image,
        Rectangle world,
        List<AffineTransformation> affineTransformations,
        int iterPerSample,
        int symmetry
    ) {
        Point point = world.createRandomPoint();
        for (int i = START; i < iterPerSample; i++) {
            var affineTransformation = getRandomElement(affineTransformations);
            var nonLinearTransformation = getRandomElement(variations);
            point = affineTransformation.apply(point);
            point = nonLinearTransformation.apply(point);
            if (i > 0) {
                double phi = 0;
                for (int sector = 0; sector < symmetry; sector++) {
                    Point sectorPoint = point.rotatedBy(phi);
                    if (world.contains(sectorPoint)) {
                        int pixelX = (int) ((sectorPoint.x() - world.x()) * image.width() / world.width());
                        int pixelY = (int) ((sectorPoint.y() - world.y()) * image.height() / world.height());
                        if (image.contains(pixelX, pixelY)) {
                            Pixel pixel = image.data()[pixelY][pixelX];
                            synchronized (pixel) {
                                pixel.color().mix(affineTransformation.color());
                            }
                        }
                    }
                    phi += 2 * PI / symmetry;
                }
            }

        }
    }

    private List<AffineTransformation> createAffineTransformations(int n) {
        var affineTransformations = new ArrayList<AffineTransformation>();
        for (int i = 0; i < n; i++) {
            affineTransformations.add(new AffineTransformation(
                AffineTransformationCoefficients.create(),
                Color.createRandomColor()
            ));
        }
        return affineTransformations;
    }
}
