package com.kamshanski.utils.dataflow;

public interface Observer<E> {
    void onChanged(E arg);
}