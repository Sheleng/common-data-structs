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

    private static <T extends Comparable> void maxHeapIfy(T[] array, int heapSize, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;

        if (left < heapSize && array[left].compareTo(array[index]) > 0) {
            largest = left;
        }
        if (right < heapSize && array[right].compareTo(array[largest]) > 0) {
            largest = right;
        }
        if (largest != index) {
            T temp = array[index];
            array[index] = array[largest];
            array[largest] = temp;
            maxHeapIfy(array, heapSize, largest);
        }
    }

    private static <T extends Comparable> void buildMaxHeap(T[] array) {
        for (int i = (array.length - 1) / 2; i >= 0; i--) {
            maxHeapIfy(array, array.length, i);
        }
    }

    public static <T extends Comparable> void sort(T[] array) {
        buildMaxHeap(array);
        int heapSize = array.length;
        for (int i = array.length - 1; i >= 1; i--) {
            T temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapSize = heapSize - 1;
            maxHeapIfy(array, heapSize, 0);
        }
    }
}
