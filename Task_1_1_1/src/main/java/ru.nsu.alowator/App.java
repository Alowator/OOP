package ru.nsu.alowator;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();

        String inputLine = scanner.nextLine();
        String[] splattedInputLine = inputLine.split("\\s+");

        for (String intString : splattedInputLine) {
            if (!intString.isEmpty()) {
                list.add(Integer.parseInt(intString));
            }
        }

        Integer[] arr = list.toArray(new Integer[0]);

        HeapSort heapsort = new HeapSort();
        heapsort.inplaceSort(arr);

        for (Integer x : arr) {
            System.out.print(x + " ");
        }
    }
}
