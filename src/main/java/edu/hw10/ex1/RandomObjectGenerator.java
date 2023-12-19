package edu.hw10.ex1;

import edu.hw10.ex1.paramGenerator.BooleanGenerator;
import edu.hw10.ex1.paramGenerator.ByteGenerator;
import edu.hw10.ex1.paramGenerator.CharGenerator;
import edu.hw10.ex1.paramGenerator.DoubleGenerator;
import edu.hw10.ex1.paramGenerator.FloatGenerator;
import edu.hw10.ex1.paramGenerator.IntegerGenerator;
import edu.hw10.ex1.paramGenerator.LongGenerator;
import edu.hw10.ex1.paramGenerator.ParameterGenerator;
import edu.hw10.ex1.paramGenerator.ShortGenerator;
import edu.hw10.ex1.paramGenerator.StringGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import lombok.SneakyThrows;

public class RandomObjectGenerator {

    public static final Map<Class, ParameterGenerator> PARAMETER_GENERATORS = Map.of(
        boolean.class, new BooleanGenerator(),
        byte.class, new ByteGenerator(),
        short.class, new ShortGenerator(),
        char.class, new CharGenerator(),
        int.class, new IntegerGenerator(),
        long.class, new LongGenerator(),
        float.class, new FloatGenerator(),
        double.class, new DoubleGenerator(),
        String.class, new StringGenerator()
    );

    private final Random random;

    public RandomObjectGenerator() {
        random = ThreadLocalRandom.current();
    }

    public RandomObjectGenerator(Random random) {
        this.random = random;
    }

    @SneakyThrows
    public <T, C> T nextObject(Class<C> clazz, String fabricMethodName) {
        if (fabricMethodName == null) {
            return (T) nextObject(clazz);
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(fabricMethodName)) {
                Object[] arguments = createArguments(method.getParameters());
                if (Modifier.isStatic(method.getModifiers())) {
                    return (T) method.invoke(null, arguments);
                }
                return (T) method.invoke(nextObject(clazz), arguments);
            }
        }
        throw new RuntimeException(
            "No method for this method name %s in class %s"
                .formatted(fabricMethodName, clazz.getName())
        );
    }

    @SneakyThrows
    public <T> T nextObject(Class<T> clazz) {
        Constructor<T> constructor = randomConstructor(clazz);
        Object[] arguments = createArguments(constructor.getParameters());
        return constructor.newInstance(arguments);

    }

    private <T> Constructor<T> randomConstructor(Class<T> clazz) {
        var constructors = clazz.getDeclaredConstructors();
        if (constructors.length == 0) {
            throw new RuntimeException("clazz represents an interface, a primitive type, an array class, or void.");
        }
        int constructorIdx = random.nextInt(constructors.length);
        return (Constructor<T>) constructors[constructorIdx];
    }

    private Object[] createArguments(Parameter[] parameters) {
        Object[] arguments = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            arguments[i] = PARAMETER_GENERATORS
                .get(parameters[i].getType())
                .generate(random, parameters[i].getAnnotations());
        }
        return arguments;
    }
}
