package edu.hw10.ex1.paramGenerator;

import java.lang.annotation.Annotation;
import java.util.Random;

public interface ParameterGenerator {

    Object generate(Random random, Annotation[] annotations);
}
