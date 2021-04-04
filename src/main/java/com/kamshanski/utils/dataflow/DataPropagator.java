package com.kamshanski.utils.dataflow;

import java.util.HashSet;

public class DataPropagator<T> {

    protected final HashSet<Observer<T>> observers = new HashSet<>();
    protected final HashSet<Constraint<T>> constraints = new HashSet<>();

    public synchronized void set(T value) {
        boolean valid = applyConstraints(value);

        if (valid)
            notifyObservers(value);
    }

    public boolean observe(Observer<T> observer) {
        if (observer == null) {
            return false;
        }

        observers.add(observer);

        return true;
    }

    public boolean addConstraint(Constraint<T> constraint) {
        if (constraint == null) {
            return false;
        }

        constraints.add(constraint);

        return true;
    }

    public void removeObserver(Observer<T> observer) {
        observers.remove(observer);
    }
    public void removeConstraint(Constraint<T> constraint) {
        constraints.remove(constraint);
    }


    protected void notifyObservers(T value) {
        for (Observer<T> observer : observers) {
            observer.onChanged(value);
        }
    }

    protected boolean applyConstraints(T value) {
        for (Constraint<T> constraint : constraints) {
            if (!constraint.inspect(value)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataPropagator[" + observers.size() + ']';
    }
}
