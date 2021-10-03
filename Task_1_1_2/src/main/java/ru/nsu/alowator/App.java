package ru.nsu.alowator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        String template = scanner.nextLine();

        FileInputStream fileInputStream = new FileInputStream(filename);

        TemplateIncludes templateIncludes = new TemplateIncludes(fileInputStream);
        List<Integer> resList = templateIncludes.findIncludes(template.toCharArray());
        for (Integer x : resList) {
            System.out.print(x + " ");
        }
    }
}
