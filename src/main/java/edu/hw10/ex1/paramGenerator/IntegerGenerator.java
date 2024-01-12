package edu.hw10.ex1.paramGenerator;

import edu.hw10.ex1.annotations.Max;
import edu.hw10.ex1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;
import static java.lang.Long.max;
import static java.lang.Long.min;

public class IntegerGenerator implements ParameterGenerator {

    @Override
    public Object generate(Random random, Annotation[] annotations) {
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        for (var annotation : annotations) {
            if (annotation instanceof Max maxAnnotation) {
                max = (int) min(max, maxAnnotation.maxValue());
            } else if (annotation instanceof Min minAnnotation) {
                min = (int) max(min, minAnnotation.minValue());
            }
        }
        return random.nextInt(min, max + 1);
    }
}
