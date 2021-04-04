package com.kamshanski.utils.dataflow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class LiveData<T> extends DataPropagator<T> {
    private volatile T value;

    public LiveData(T value) {
        this.value = value;
    }

    public synchronized T get() {
        return value;
    }

    @Override
    public synchronized void set(T value) {
        if (applyConstraints(value)) {
            this.value = value;
            notifyObservers(this.value);
        }

    }

    @Override
    public boolean observe(Observer<T> observer) {
        return observe(observer, true);
    }

    public boolean observe(Observer<T> observer, boolean doFirstNotification) {
        boolean valid = super.observe(observer);

        if (doFirstNotification && valid) {
            observer.onChanged(value);
        }

        return valid;
    }


    @Override
    public String toString() {
        return "LiveData{" + value + '}';
    }
}
