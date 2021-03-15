package com.kamshanski.utils.collections.primitivearraylists;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class ArrayListDouble {

    public final int sizeInclement;
    double[] array;
    int size = 0;

    public ArrayListDouble(int initialCapacity, int sizeIncrement) {
        this.array = new double[initialCapacity];
        this.sizeInclement = sizeIncrement;
    }

    public ArrayListDouble() {
        this(15, 15);
    }

    public void add(double d) {
        if (array.length <= size) {
            double[] newArray = new double[array.length + sizeInclement];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[size] = d;
        size++;
    }

    public void add(double[] d) {
        for (double t : d) {
            add(t);
        }
    }

    public double get(int index) {
        if (index < 0) {
            index += size;
        } else if (index >= size){
            index -= size;
        }
        return array[index];
    }

    public int size() {
        return size;
    }

    public double getSum() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += array[i];
        }
        return sum;
    }

    public ArrayListDouble invert() {
        ArrayListDouble.invert(array, size);
        return this;
    }

    public double[] toArray() {
        double[] ar;
        ar = new double[size];
        System.arraycopy(array, 0, ar, 0, size);
        return ar;
    }

    public void clear() {
        size = 0;
    }

    public static double[] invert(double[] array) {
        return invert(array, array.length);
    }

    public static double[] invert(double[] array, int size) {
        for(int i = 0; i<size / 2; i++){
            double temp = array[i];
            array[i] = array[size - i - 1];
            array[size - i - 1] = temp;
        }
        return array;
    }

    // src = [1,2,3,4,5], dest = [10,20,30,40,50,60,70,80]
    // shiftAndFillBeginning(src, dest) = [1,2,3,4,5, 10,20,30]
    public static double[] shiftAndFillBeginning(double[] src, double[] dest) {
        int slen = src.length;
        int dlen = dest.length;
        for (int i = dlen - 1; i > -1; i--) {
            dest[i] = (i >= slen) ?
                    dest[i-slen] :
                    src[i];
        }
        return dest;
    }

    // Only for size > orig
    public static double[] interpolate(double[] orig, int size) {
        if (orig.length < 1 || size < 1) {
            return ArrayListDouble.arrayOf(0,1);
        }

        if (orig.length < 2) {
            return ArrayListDouble.arrayOf(orig[0], size);
        }

        if (orig.length < size) {
            int dS = size - 1;
            int oS = orig.length - 1;
            double[] dest = new double[size];
            int lastX = 1;
            double Y1 = orig[0], Y2 = orig[1], X1 = 0, X2 = 1;
            double k = (Y1 - Y2) / (X1 - X2);
            for (int i = 0; i < size; i++) {
                double X = oS * (double) i / dS;
                if (X > lastX) {
                    lastX++;
                    X1 = lastX - 1;
                    X2 = lastX;
                    Y1 = orig[lastX - 1];
                    Y2 = orig[lastX];
                    k = (Y1 - Y2) / (X1 - X2);
                }
                dest[i] = round(Y2 + k * (X - X2), 5);
            }

            return dest;
        }
        return orig;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double[] arrayOf(double value, int size) {
        double[] ar = new double[size];
        Arrays.fill(ar, value);
        return ar;
    }
}
