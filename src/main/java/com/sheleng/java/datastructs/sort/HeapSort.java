package com.sheleng.java.datastructs.sort;

/**
 * HeapSort
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class HeapSort {

    /**
     * 取消默认构造函数
     */
    private HeapSort() {
        throw new RuntimeException("HeapSort.class can't be instantiated.");
    }

    private static <T extends Comparable> void swap(T[] array, int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public static <T extends Comparable> void maxHeapFix(T[] array, int index, int heapSize) {
        int largest = index;
        int lChild = (index << 1) + 1;
        int rChild = (index << 1) + 2;

        if (lChild < heapSize && array[lChild].compareTo(array[largest]) > 0) {
            largest = lChild;
        }
        if (rChild < heapSize && array[rChild].compareTo(array[largest]) > 0) {
            largest = rChild;
        }
        if (largest == index) {
            return;
        }
        swap(array, index, largest);
        maxHeapFix(array, largest, heapSize);
    }

    private static <T extends Comparable> void buildMaxHeap(T[] array) {
        for (int i = (array.length - 1) / 2; i >= 0; i--) {
            maxHeapFix(array, i, array.length);
        }
    }

    public static <T extends Comparable> void sort(T[] array) {
        int heapSize = array.length;
        buildMaxHeap(array);
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            heapSize--;
            maxHeapFix(array, 0, heapSize);
        }
    }
}
