package edu.hw10.ex1.paramGenerator;

import java.lang.annotation.Annotation;
import java.util.Random;

public class BooleanGenerator implements ParameterGenerator {

    @Override
    public Object generate(Random random, Annotation[] annotations) {
        return random.nextBoolean();
    }
}
