package com.kamshanski.utils.collections.slidingwindowarrays;

import java.util.Arrays;

// head is at last input
public class SlidingWindowDoubleArray implements Cloneable {
    double[] window;
    int size = 0;
    final int maxSize;
    int index = 0;

    public SlidingWindowDoubleArray(int size, double initValue) {
        this(size);
        Arrays.fill(window, initValue);
    }

    public SlidingWindowDoubleArray(int size) {
        window = new double[50];
        this.maxSize = size;
    }

    public void put(double datum) {
        window[index] = datum;
        ++index;
        size += size < maxSize ? 1 : 0;
        // Mode start index
        if (index >= maxSize) {
            index -= maxSize;
        }
    }

    public double get(int i) {
        int ind = index + i;
        if (ind < 0) {
            while (ind < 0) {
                ind += size;
            }
        } else if (ind >= size) {
            while (ind >= size) {
                ind -= size;
            }
        }
        return window[ind];
    }

//    public int clear(int newSize) {
//
//    }

    public double[] toArray() {
        double[] window = new double[size];
        for (int i = 0; i < window.length; i++) {
            window[i] = get(i);
        }
        return window;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "SlidingWindowDoubleArray{" +
                "window=" + Arrays.toString(window) +
                ", size=" + size +
                ", index=" + index +
                '}';
    }
}
