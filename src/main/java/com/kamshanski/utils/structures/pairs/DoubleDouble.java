package com.kamshanski.utils.structures.pairs;

public class DoubleDouble {
    public double first;
    public double second;

    public DoubleDouble(double first, double second) {
        this.first = first;
        this.second = second;
    }

    public void add(double dFirst, double dSecond) {
        first += dFirst;
        second += dSecond;
    }
}
