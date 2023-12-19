package edu.hw10.ex1.paramGenerator;

import edu.hw10.ex1.annotations.Max;
import edu.hw10.ex1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;
import static java.lang.Long.max;
import static java.lang.Long.min;

public class ShortGenerator implements ParameterGenerator {

    @Override
    public Object generate(Random random, Annotation[] annotations) {
        short max = Short.MAX_VALUE;
        short min = Short.MIN_VALUE;
        for (var annotation : annotations) {
            if (annotation instanceof Max maxAnnotation) {
                max = (short) min(max, maxAnnotation.maxValue());
            } else if (annotation instanceof Min minAnnotation) {
                min = (short) max(min, minAnnotation.minValue());
            }
        }
        return (short) random.nextInt(min, max + 1);
    }
}
