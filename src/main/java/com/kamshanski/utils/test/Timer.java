package com.kamshanski.utils.test;

import java.util.HashMap;

public class Timer {
    private static HashMap<String, Long> timestamps = new HashMap<>();

    public static void start(String name) {
        timestamps.put(name, System.nanoTime());
    }

    public static long finish(String name, boolean deleteTimestamp) {
        long end = System.nanoTime();
        long start;
        try {
            if (deleteTimestamp) {
                start = timestamps.remove(name);
            } else {
                start = timestamps.get(name);
            }
            return end - start;
        } catch (NullPointerException npe) {
            return -1;
        }
    }

    public static void print(String name) {
        long dt = finish(name, false);
        System.out.println(name + ": " + (dt/1000000) + " ms");
    }
}
