package ru.nsu.alowator;


public class HeapSort {
    public void inplaceSort(Integer[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, arr.length, i);
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private void swap(Integer[] arr, int i1, int i2) {
        Integer t = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = t;
    }

    private void heapify(Integer[] arr, int actualSize, int index) {
        int root = index;
        int l = 2 * root + 1, r = 2 * root + 2;

        if (l < actualSize && arr[l] > arr[root]) {
            root = l;
        }
        if (r < actualSize && arr[r] > arr[root]) {
            root = r;
        }

        if (root != index) {
            swap(arr, index, root);
            heapify(arr, actualSize, root);
        }
    }
}
