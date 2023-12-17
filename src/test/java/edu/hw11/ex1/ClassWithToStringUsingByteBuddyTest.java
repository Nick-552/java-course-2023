package edu.hw11.ex1;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ClassWithToStringUsingByteBuddyTest {

    DynamicType.Unloaded<Object> unloadedType = new ByteBuddy()
        .subclass(Object.class)
        .method(ElementMatchers.isToString())
        .intercept(FixedValue.value("Hello World ByteBuddy!"))
        .make();

    Class<?> dynamicType = unloadedType.load(
        getClass().getClassLoader()
    ).getLoaded();

    @SneakyThrows
    @Test
    public void toString_shouldReturnHelloWorldByteBuddy() {
        assertThat(dynamicType
            .getDeclaredConstructor().newInstance()
            .toString()
        ).isEqualTo("Hello World ByteBuddy!");
    }
}
