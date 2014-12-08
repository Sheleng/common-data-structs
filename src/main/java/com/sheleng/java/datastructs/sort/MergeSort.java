package com.sheleng.java.datastructs.sort;

import java.util.ArrayList;

/**
 * MergeSort
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class MergeSort {

    protected static <T extends Comparable> void merge(T[] array, int begin, int mid, int end) {
        ArrayList<T> left = new ArrayList<T>();
        for (int i = begin; i <= mid; i++) {
            left.add(array[i]);
        }
        ArrayList<T> right = new ArrayList<T>();
        for (int i = mid + 1; i <= end; i++) {
            right.add(array[i]);
        }

        int i = 0;
        int j = 0;
        for (int k = begin; k <= end; k++) {
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

    public static <T extends Comparable> void sort(T[] array, int begin, int end) {
        if (array == null) {
            throw new IllegalArgumentException("array == null.");
        }
        if (begin < end) {
            int mid = (begin + end) / 2;
            sort(array, begin, mid);
            sort(array, mid + 1, end);
            merge(array, begin, mid, end);
        }
    }
}
