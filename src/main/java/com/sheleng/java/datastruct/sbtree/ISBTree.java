package com.sheleng.java.datastruct.sbtree;

import java.util.Iterator;
import java.util.Map;

/**
 * ISBTree
 *
 * @author Sheleng
 * @version 0.0.1
 */
public interface ISBTree<K extends Comparable, V> extends Iterator<Map.Entry<K, V>> {

    void insert(K key, V value);

    V delete(K key);

    V find(K key);

    Map.Entry findEntry(K key);

    V max();

    V min();

    int size();

    void clear();
}
