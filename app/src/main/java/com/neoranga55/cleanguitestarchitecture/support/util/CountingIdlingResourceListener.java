package com.neoranga55.cleanguitestarchitecture.support.util;

/**
 * Interface to notify of resources being busy or idle
 */
public interface CountingIdlingResourceListener {
    void increment();
    void decrement();
}
