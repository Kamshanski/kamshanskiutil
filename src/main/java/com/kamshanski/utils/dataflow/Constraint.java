package com.kamshanski.utils.dataflow;

// check for propagated value for sanity
// returns true if it's ok
public interface Constraint<E> {
    boolean inspect(E arg);
}
