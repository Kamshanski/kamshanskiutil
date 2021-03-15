package com.kamshanski.utils.structures.pairs;

public class IntDouble {
    public int first;
    public double second;

    public IntDouble(int first, double second) {
        this.first = 0;
        this.second = 0;
    }

    public void add(int dFirst, double dSecond) {
        first += dFirst;
        second += dSecond;
    }
}
