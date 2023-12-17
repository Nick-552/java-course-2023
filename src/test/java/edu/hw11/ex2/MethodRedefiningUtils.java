package edu.hw11.ex2;

import lombok.experimental.UtilityClass;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

@UtilityClass
public class MethodRedefiningUtils {

    public static void redefineArithmeticUtilsSumMethod() {
        ByteBuddyAgent.install();
        try (var unloaded = new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(named("sum").and(takesArguments(int.class, int.class)))
            .intercept(MethodDelegation.to(InterceptedSum.class))
            .make()
        ) {
            unloaded.load(
                ArithmeticUtils.class.getClassLoader(),
                ClassReloadingStrategy.fromInstalledAgent()
            );
        }
    }

    class InterceptedSum {

        static int intercept(int a, int b) {
            return a * b;
        }
    }
}
