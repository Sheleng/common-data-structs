package com.sheleng.java.datastructs.sort;

/**
 * QuickSort
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class QuickSort {

    /**
     * 取消默认构造函数
     */
    private QuickSort() {
        throw new RuntimeException("QuickSort.class can't be instantiated.");
    }

    public static <T extends Comparable> void sort(T[] array, int p, int r) {
        if (p < r) {
            int q = partition(array, p, r);
            sort(array, p, q - 1);
            sort(array, q + 1, r);
        }
    }

    private static <T extends Comparable> int partition(T[] array, int p, int r) {
        T x = array[r];
        int i = p - 1;
        for (int j = p; j <= r - 1; j++) {
            if (array[j].compareTo(x) < 0) {
                i = i + 1;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, r);
        return i + 1;
    }

    private static <T extends Comparable> void swap(T[] array, int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
}
