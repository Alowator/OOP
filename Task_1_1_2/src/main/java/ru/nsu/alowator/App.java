package ru.nsu.alowator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String filename, template;
        char[] buff = new char[4096];
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
            int charsRead = inputStreamReader.read(buff);
            filename = new String(Arrays.copyOf(buff, charsRead - 1));
            charsRead = inputStreamReader.read(buff);
            template = new String(Arrays.copyOf(buff, charsRead - 1));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        TemplateIncludes templateIncludes = new TemplateIncludes(filename);
        List<Integer> resList;
        try {
            resList = templateIncludes.findIncludes(template);
            for (Integer x : resList) {
                System.out.print(x + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
