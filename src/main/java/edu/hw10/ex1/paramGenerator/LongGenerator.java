package edu.hw10.ex1.paramGenerator;

import edu.hw10.ex1.annotations.Max;
import edu.hw10.ex1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;
import static java.lang.Long.max;
import static java.lang.Long.min;

public class LongGenerator implements ParameterGenerator {
    @Override
    public Object generate(Random random, Annotation[] annotations) {
        long max = Long.MAX_VALUE;
        long min = Long.MIN_VALUE;
        for (var annotation : annotations) {
            if (annotation instanceof Max maxAnnotation) {
                max = min(max, maxAnnotation.maxValue());
            } else if (annotation instanceof Min minAnnotation) {
                min = max(min, minAnnotation.minValue());
            }
        }
        return random.nextLong(min, max + 1);
    }
}
