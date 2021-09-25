package ru.nsu.alowator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }

        Integer[] arr = list.toArray(new Integer[0]);

        HeapSort heapsort = new HeapSort();
        heapsort.inplaceSort(arr);

        for (Integer x : arr) {
            System.out.print(x + " ");
        }
    }
}
