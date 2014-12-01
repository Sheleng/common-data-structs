package com.sheleng.java.datastructs.sbtree;


import java.util.Map;

/**
 * SBNode
 *
 * @author Sheleng
 * @version 0.0.1
 */
class SBNode<K extends Comparable, V> {

    private int size = 1;
    private SBNode<K, V> parent, left, right;
    private SBTree.Entry<K, V> entry;

    public SBNode parent(SBNode parent) {
        this.parent = parent;
        return this;
    }

    public SBNode<K, V> parent() {
        return parent;
    }

    public SBNode left(SBNode left) {
        this.left = left.parent(this);
        return this;
    }

    public SBNode<K, V> left() {
        return left;
    }

    public SBNode right(SBNode right) {
        this.right = right.parent(this);
        return this;
    }

    public SBNode<K, V> right() {
        return right;
    }

    public void size(int size) {
        this.size = size;
    }

    public int size() {
        return size;
    }

    public K key() {
        return entry.getKey();
    }

    public void value(V value) {
        if (value == null) {
            throw new IllegalArgumentException("value == null.");
        }
        entry.setValue(value);
    }

    public V value() {
        return entry.value;
    }

    public Map.Entry entry() {
        return entry;
    }

    public SBNode() {
    }

    public SBNode(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key == null.");
        }
        entry = new SBTree.Entry<K, V>(key, value);
    }
}
