package edu.hw1.ex7;

public final class CyclicBitShift {
    private CyclicBitShift() {}

    public static int rotateLeft(int n, int shift) {
        if (n <= 0 || shift < 0) {
            return -1;
        }
        int bitsAmount = Integer.toBinaryString(n).length();
        int highestOneBit = Integer.highestOneBit(n);
        int shiftNew = shift % bitsAmount; // Removing full cycles
        return ((n << shiftNew) | (n >> bitsAmount - shiftNew)) % (highestOneBit * 2);
    }

    public static int rotateRight(int n, int shift) {
        if (n <= 0 || shift < 0) {
            return -1;
        }
        int bitsAmount = Integer.toBinaryString(n).length();
        int highestOneBit = Integer.highestOneBit(n);
        int shiftNew = shift % bitsAmount; // Removing full cycles
        return ((n >> shiftNew) | (n << bitsAmount - shiftNew)) % (highestOneBit * 2);
    }
}
