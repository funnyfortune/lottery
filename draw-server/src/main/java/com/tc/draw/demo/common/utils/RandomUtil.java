package com.tc.draw.demo.common.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

    private RandomUtil() {
    }

    public static int getInt(int start, int end) {
        if (start == end) {
            return start;
        }
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(start, end);
    }

    public static long getLong(long start, long end) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextLong(start, end);
    }

    public static double getDouble(double start, double end) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextDouble(start, end);
    }
}
