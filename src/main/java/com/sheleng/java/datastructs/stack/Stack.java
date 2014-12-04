package com.sheleng.java.datastructs.stack;

/**
 * Stack
 *
 * @author Sheleng
 * @version 0.0.1
 */
public interface Stack<T> {

    void push(T value);

    T pop();

    T peek();

    boolean isEmpty();
}
