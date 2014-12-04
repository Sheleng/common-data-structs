package com.sheleng.java.datastructs.stack;

/**
 * LinkedStack
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class LinkedStack<T> implements Stack<T> {

    /* end哨兵 */
    private Node<T> top = new Node<T>();

    @Override
    public void push(T value) {
        top = new Node<T>(value, top);
    }

    public T pop() {
        T result = top.item;
        if (!top.end()) {
            top = top.next;
        }
        return result;
    }

    public T peek() {
        return top.item;
    }

    public boolean isEmpty() {
        return top.end();
    }

    private static class Node<T> {

        T item;
        Node<T> next;

        public Node() {
        }

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }

        public boolean end() {
            return item == null && next == null;
        }
    }
}
