package edu.project4;

import edu.project4.model.FractalImage;
import edu.project4.model.Rectangle;
import edu.project4.renderer.ParallelRenderer;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.nonLinear.DiscTransformation;
import edu.project4.transformation.nonLinear.ExponentialTransformation;
import edu.project4.transformation.nonLinear.EyefishTransformation;
import edu.project4.transformation.nonLinear.HandkerchiefTransformation;
import edu.project4.transformation.nonLinear.JuliaTransformation;
import edu.project4.transformation.nonLinear.PowerTransformation;
import edu.project4.transformation.nonLinear.SinusoidalTransformation;
import edu.project4.transformation.nonLinear.SphericalTransformation;
import edu.project4.transformation.nonLinear.SpiralTransformation;
import edu.project4.transformation.nonLinear.SwirlTransformation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import static edu.project4.utils.ListUtils.getRandomElement;

@UtilityClass
public class FractalFlameGenerator {

    private static final List<Class<? extends Transformation>> CLASSES = List.of(
        DiscTransformation.class,
        ExponentialTransformation.class,
        EyefishTransformation.class,
        HandkerchiefTransformation.class,
        JuliaTransformation.class,
        PowerTransformation.class,
        SinusoidalTransformation.class,
        SphericalTransformation.class,
        SpiralTransformation.class,
        SwirlTransformation.class
    );

    @SuppressWarnings("checkstyle:MagicNumber")
    @SneakyThrows
    public static FractalImage generateRandom(boolean symmetry) {
        var random = ThreadLocalRandom.current();
        List<Transformation> transformations = new ArrayList<>();
        int numberOfTransformations = random.nextInt(2, 7);
        int numberOfSamples = random.nextInt(3, 10);
        int symmetryNumber = (symmetry ? random.nextInt(2, 12) : 1);
        for (int i = 0; i < numberOfTransformations; i++) {
            var tClass = getRandomElement(CLASSES);
            transformations.add(tClass.newInstance());
        }
        var renderer = new ParallelRenderer(
            3840,
            2160,
            transformations
        );
        return renderer.render(
            Rectangle.getDefaultRectangle(),
            numberOfSamples,
            100000,
            symmetryNumber
        );
    }
}
