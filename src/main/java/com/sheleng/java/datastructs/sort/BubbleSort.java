package com.sheleng.java.datastructs.sort;

/**
 * BubbleSort
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class BubbleSort {

    /**
     * 取消默认构造函数
     */
    private BubbleSort(){
        throw new RuntimeException("BubbleSort.class can't be instantiated");
    }

    public static <T extends Comparable> void sort(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("array == null.");
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j].compareTo(array[i]) < 0) {
                    T temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
    }
}
