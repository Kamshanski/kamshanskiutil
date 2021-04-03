package com.kamshanski.utils.dataflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class LiveData<T>{
    private volatile T value;
    private final HashMap<String, Observer<T>> observers = new HashMap<>();
    private final HashMap<String, Constraint<T>> constraints = new HashMap<>();

    public LiveData(T value) {
        this.value = value;
    }

    public synchronized T get() {
        return value;
    }

    public synchronized void set(T value) {
        boolean valid = true;
        for (Constraint constraint : constraints.values()) {
            valid = constraint.inspect(value);
            if (!valid) {
                break;
            }
        }

        if (valid) {
            this.value = value;
        }

        notifyObservers(this.value);
    }

    public String observe(Observer<T> observer) {
        return observe(null, observer);
    }

    public String observe(String tag, Observer<T> observer) {
        if (tag == null) {
            tag = String.valueOf(observer.hashCode());
        }

        observers.put(tag, observer);

        return tag;
    }

    public String addConstraint(Constraint<T> constraint) {
        return addConstraint(null, constraint);
    }

    public String addConstraint(String tag, Constraint<T> constraint) {
        if (tag == null) {
            tag = String.valueOf(constraint.hashCode());
        }

        constraints.put(tag, constraint);

        return tag;
    }

    private void notifyObservers(T value) {
        for (Observer<T> observer : observers.values()) {
            observer.onChanged(value);
        }
    }

    // check for new value for sanity
    // returns true if it's ok
    public interface Constraint<E> {
        boolean inspect(E arg);
    }

    public interface Observer<E> {
        void onChanged(E arg);
    }
}
