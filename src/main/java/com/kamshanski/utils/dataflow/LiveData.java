package com.kamshanski.utils.dataflow;

import java.util.ArrayList;

public class LiveData<T>{
    private volatile T value;
    private final ArrayList<Observer<T>> observers = new ArrayList<>();

    public LiveData(T value) {
        this.value = value;
    }

    public synchronized T get() {
        return value;
    }

    public synchronized void set(T value) {
        this.value = value;
        notifyObservers(this.value);
    }

    public void observe(Observer<T> observer) {
        observers.add(observer);
    }

    private void notifyObservers(T value) {
        for (Observer<T> observer : observers) {
            observer.onChanged(value);
        }
    }

    public interface Observer<E> {
        void onChanged(E arg);
    }
}
