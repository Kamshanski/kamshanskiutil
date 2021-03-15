package com.kamshanski.utils.structures.wrappers;

public class Mutable {


    public static class Int {
        public int v;
        public Int(int v) {
            this.v = v;
        }
        public Int add(int v) {
            this.v += v;
            return this;
        }
        public Int add(Int v) {
            this.v += v.v;
            return this;
        }
    }
    public static class Double {
        public double v;
        public Double(double v) {
            this.v = v;
        }
        public Double add(double v) {
            this.v += v;
            return this;
        }
        public Double add(Double v) {
            this.v += v.v;
            return this;
        }
    }

}
