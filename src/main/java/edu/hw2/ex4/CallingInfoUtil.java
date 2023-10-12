package edu.hw2.ex4;

public final class CallingInfoUtil {
    private CallingInfoUtil() {}

    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace.length >= 2) {
            String className = stackTrace[1].getClassName();
            String methodName = stackTrace[1].getMethodName();
            return new CallingInfo(className, methodName);
        } else {
            return null;
        }
    }
}
