import java.util.Objects;

public class Pair<A, B> {
    public final A fst;
    public final B snd;

    public Pair(A fst, B snd) {
        this.fst = fst;
        this.snd = snd;
    }

    public String toString() {
        return "Pair[" + fst + "," + snd + "]";
    }

    public boolean equals(Object other) {
        return
                other instanceof Pair<?,?> &&
                        Objects.equals(fst, ((Pair<?,?>)other).fst) &&
                        Objects.equals(snd, ((Pair<?,?>)other).snd);
    }

    public int hashCode() {
        if (fst == null) return (snd == null) ? 0 : snd.hashCode() + 1;
        else if (snd == null) return fst.hashCode() + 2;
        else return fst.hashCode() * 17 + snd.hashCode();
    }

    public static <A,B> Pair<A,B> of(A a, B b) {
        return new Pair<>(a,b);
    }


    public static class Mutable<F, S> {
        public F first;
        public S second;

        public Mutable(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    public static class IntDouble {
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

    public static class IntInt {
        public int first;
        public int second;

        public IntInt(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static class DoubleDouble {
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
}
