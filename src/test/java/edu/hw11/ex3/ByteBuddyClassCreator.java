package edu.hw11.ex3;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.Opcodes;

public class ByteBuddyClassCreator {

    public static Class<?> createClassWithFibMethod() {
        try (var unloaded = new ByteBuddy()
            .subclass(Object.class)
            .name("FibonacciUtils")
            .defineMethod("fib", long.class, Ownership.STATIC, Visibility.PUBLIC)
            .withParameter(int.class, "n")
            .intercept(
                new Implementation.Simple(
                    (mv, context, methodDescription) -> {
                        Label label1 = new Label();
                        Label label2 = new Label();

                        mv.visitCode();

                        // n < 0 then return -1
                        mv.visitVarInsn(Opcodes.ILOAD, 0);
                        mv.visitJumpInsn(Opcodes.IFGE, label1);
                        mv.visitLdcInsn(-1);
                        mv.visitInsn(Opcodes.I2L);
                        mv.visitInsn(Opcodes.LRETURN);

                        // n < 2 then return n
                        mv.visitLabel(label1);
                        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        mv.visitVarInsn(Opcodes.ILOAD, 0);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitJumpInsn(Opcodes.IF_ICMPGE, label2);
                        mv.visitVarInsn(Opcodes.ILOAD, 0);
                        mv.visitInsn(Opcodes.I2L);
                        mv.visitInsn(Opcodes.LRETURN);

                        // else return fib(n - 1) + fib(n - 2)
                        mv.visitLabel(label2);
                        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        mv.visitVarInsn(Opcodes.ILOAD, 0);
                        mv.visitInsn(Opcodes.ICONST_1);
                        mv.visitInsn(Opcodes.ISUB);
                        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "FibonacciUtils", "fib", "(I)J", false);
                        mv.visitVarInsn(Opcodes.ILOAD, 0);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitInsn(Opcodes.ISUB);
                        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "FibonacciUtils", "fib", "(I)J", false);
                        mv.visitInsn(Opcodes.LADD);
                        mv.visitInsn(Opcodes.LRETURN);
                        mv.visitEnd();

                        return new ByteCodeAppender.Size(5, 1);
                    }
                )
            ).make()) {
            return unloaded.load(ClassLoader.getSystemClassLoader()).getLoaded();
        }
    }
}
