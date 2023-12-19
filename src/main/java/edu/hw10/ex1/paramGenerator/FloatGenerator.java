package edu.hw10.ex1.paramGenerator;

import edu.hw10.ex1.annotations.Max;
import edu.hw10.ex1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;
import static java.lang.Float.max;
import static java.lang.Float.min;

public class FloatGenerator implements ParameterGenerator {

    @Override
    public Object generate(Random random, Annotation[] annotations) {
        float max = Float.MAX_VALUE;
        float min = -Float.MAX_VALUE;
        for (var annotation : annotations) {
            if (annotation instanceof Max maxAnnotation) {
                max = min(max, maxAnnotation.maxValue());
            } else if (annotation instanceof Min minAnnotation) {
                min = max(min, minAnnotation.minValue());
            }
        }
        return random.nextFloat(min, max + 1);
    }
}
