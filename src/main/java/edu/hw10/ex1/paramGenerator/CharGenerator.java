package edu.hw10.ex1.paramGenerator;

import edu.hw10.ex1.annotations.Max;
import edu.hw10.ex1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;
import static java.lang.Long.max;
import static java.lang.Long.min;

public class CharGenerator implements ParameterGenerator {

    @Override
    public Object generate(Random random, Annotation[] annotations) {
        char max = Character.MAX_VALUE;
        char min = Character.MIN_VALUE;
        for (var annotation : annotations) {
            if (annotation instanceof Max maxAnnotation) {
                max = (char) min(max, maxAnnotation.maxValue());
            } else if (annotation instanceof Min minAnnotation) {
                min = (char) max(min, minAnnotation.minValue());
            }
        }
        return (char) random.nextInt(min, max + 1);
    }
}
