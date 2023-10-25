package edu.hw2.ex4;

import org.jetbrains.annotations.NotNull;

public final class CallingInfoUtil {
    private CallingInfoUtil() {}

    public static @NotNull CallingInfo callingInfo() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String className = stackTrace[1].getClassName();
        String methodName = stackTrace[1].getMethodName();
        return new CallingInfo(className, methodName);
    }
}
