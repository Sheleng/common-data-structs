package com.sheleng.java.datastructs.sort;

/**
 * InsertionSort
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class InsertionSort {

    public static <T extends Comparable> void asc(T[] array) {
        sort(array, true);
    }

    public static <T extends Comparable> void desc(T[] array) {
        sort(array, false);
    }

    protected static <T extends Comparable> void sort(T[] array, boolean isAsc) {
        if (array == null) {
            throw new IllegalArgumentException("array == null.");
        }

        for (int index = 1; index < array.length; index++) {
            T item = array[index];
            int i = index - 1;
            while (i >= 0 && !(isAsc ^ array[i].compareTo(item) > 0)) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = item;
        }
    }
}
