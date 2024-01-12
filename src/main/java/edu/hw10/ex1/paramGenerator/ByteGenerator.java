package edu.hw10.ex1.paramGenerator;

import edu.hw10.ex1.annotations.Max;
import edu.hw10.ex1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;
import static java.lang.Long.max;
import static java.lang.Long.min;

public class ByteGenerator implements ParameterGenerator {

    @Override
    public Object generate(Random random, Annotation[] annotations) {
        byte max = Byte.MAX_VALUE;
        byte min = Byte.MIN_VALUE;
        for (var annotation : annotations) {
            if (annotation instanceof Max maxAnnotation) {
                max = (byte) min(max, maxAnnotation.maxValue());
            } else if (annotation instanceof Min minAnnotation) {
                min = (byte) max(min, minAnnotation.minValue());
            }
        }
        return (byte) random.nextInt(min, max + 1);
    }
}
