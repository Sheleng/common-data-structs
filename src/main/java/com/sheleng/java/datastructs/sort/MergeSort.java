package com.sheleng.java.datastructs.sort;

import java.util.ArrayList;

/**
 * MergeSort
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class MergeSort {

    protected static <T extends Comparable> void merge(T[] array, int p, int q, int r) {
        ArrayList<T> left = new ArrayList<T>();
        for (int i = p; i <= q; i++) {
            left.add(array[i]);
        }
        ArrayList<T> right = new ArrayList<T>();
        for (int i = q + 1; i <= r; i++) {
            right.add(array[i]);
        }

        int i = 0;
        int j = 0;
        for (int k = p; k <= r; k++) {
            if (i < left.size() && j < right.size()) {
                if (left.get(i).compareTo(right.get(j)) < 0) {
                    array[k] = left.get(i);
                    i++;
                } else {
                    array[k] = right.get(j);
                    j++;
                }
            } else if (i >= left.size() && j < right.size()) {
                array[k] = right.get(j);
                j++;
            } else if (j >= right.size() && i < left.size()) {
                array[k] = left.get(i);
                i++;
            }
        }
    }

    public static <T extends Comparable> void sort(T[] array, int p, int r) {
        if (array == null) {
            throw new IllegalArgumentException("array == null.");
        }
        if (p < r) {
            int q = (p + r) / 2;
            sort(array, p, q);
            sort(array, q + 1, r);
            merge(array, p, q, r);
        }
    }
}
