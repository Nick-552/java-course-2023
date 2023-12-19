package edu.hw10.ex1.paramGenerator;

import edu.hw10.ex1.annotations.Max;
import edu.hw10.ex1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;
import static java.lang.Double.max;
import static java.lang.Double.min;

public class DoubleGenerator implements ParameterGenerator {

    @Override
    public Object generate(Random random, Annotation[] annotations) {
        double max = Integer.MAX_VALUE;
        double min = Integer.MIN_VALUE;
        for (var annotation : annotations) {
            if (annotation instanceof Max maxAnnotation) {
                max = min(max, maxAnnotation.maxValue());
            } else if (annotation instanceof Min minAnnotation) {
                min = max(min, minAnnotation.minValue());
            }
        }
        return random.nextDouble(min, max + 1);
    }
}
