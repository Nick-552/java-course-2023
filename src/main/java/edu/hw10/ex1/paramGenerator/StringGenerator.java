package edu.hw10.ex1.paramGenerator;

import edu.hw10.ex1.annotations.Max;
import edu.hw10.ex1.annotations.Min;
import edu.hw10.ex1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import static java.lang.Long.max;
import static java.lang.Long.min;

public class StringGenerator implements ParameterGenerator {

    private static final int MAX_LENGTH = 15;

    @Override
    public Object generate(Random random, Annotation[] annotations) {
        int max = MAX_LENGTH;
        int min = 0;
        boolean canBeNull = true;
        for (var annotation : annotations) {
            if (annotation instanceof Max maxAnnotation) {
                max = (int) min(max, maxAnnotation.maxValue());
            } else if (annotation instanceof Min minAnnotation) {
                min = (int) max(min, minAnnotation.minValue());
            } else if (annotation instanceof NotNull) {
                canBeNull = false;
            }
        }
        if (max >= min) {
            return RandomStringUtils.randomAlphabetic(min, max + 1);
        }
        return canBeNull ? null : "";
    }
}
