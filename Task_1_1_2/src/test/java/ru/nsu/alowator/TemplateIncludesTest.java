package ru.nsu.alowator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class TemplateIncludesTest {

    private String testFilename = "input_test.txt";

    void writeTestFile(String text) throws IOException {
        FileWriter fileWriter = new FileWriter(testFilename);
        fileWriter.write(text);
        fileWriter.close();

    }

    @Test
    void findIncludes_empty() throws IOException {
        char[] template = "pie".toCharArray();
        String text = "I want a eye!";
        Integer[] expected = {};

        writeTestFile(text);

        FileInputStream fileInputStream = new FileInputStream(testFilename);

        TemplateIncludes templateIncludes = new TemplateIncludes(fileInputStream);
        List<Integer> resList = templateIncludes.findIncludes(template);

        Assertions.assertArrayEquals(expected, resList.toArray());
    }

    @Test
    void findIncludes_sample() throws IOException {
        char[] template = "pie".toCharArray();
        String text = "I want a pie!";
        Integer[] expected = {9};

        writeTestFile(text);

        FileInputStream fileInputStream = new FileInputStream(testFilename);

        TemplateIncludes templateIncludes = new TemplateIncludes(fileInputStream);
        List<Integer> resList = templateIncludes.findIncludes(template);

        Assertions.assertArrayEquals(expected, resList.toArray());
    }

    @Test
    void findIncludes_twice() throws IOException {
        char[] template = "pie".toCharArray();
        String text = "I want a pie, big pie!!!";
        Integer[] expected = {9, 18};

        writeTestFile(text);

        FileInputStream fileInputStream = new FileInputStream(testFilename);

        TemplateIncludes templateIncludes = new TemplateIncludes(fileInputStream);
        List<Integer> resList = templateIncludes.findIncludes(template);

        Assertions.assertArrayEquals(expected, resList.toArray());
    }

    @Test
    void findIncludes_intersection() throws IOException {
        char[] template = "lol".toCharArray();
        String text = "lololol";
        Integer[] expected = {0, 2, 4};

        writeTestFile(text);

        FileInputStream fileInputStream = new FileInputStream(testFilename);

        TemplateIncludes templateIncludes = new TemplateIncludes(fileInputStream);
        List<Integer> resList = templateIncludes.findIncludes(template);

        Assertions.assertArrayEquals(expected, resList.toArray());
    }

    @Test
    void findIncludes_extraSymbols() throws IOException {
        char[] template = " `; a;d,;/234=-".toCharArray();
        String text = "   `; a;d,;/234=-@/\\";
        Integer[] expected = {2};

        writeTestFile(text);

        FileInputStream fileInputStream = new FileInputStream(testFilename);

        TemplateIncludes templateIncludes = new TemplateIncludes(fileInputStream);
        List<Integer> resList = templateIncludes.findIncludes(template);

        Assertions.assertArrayEquals(expected, resList.toArray());
    }

    @Test
    void findIncludes_bigText() throws IOException {
        char[] template = "very big text, and this is interesting kek lul!!!".toCharArray();
        List<Integer> expected = new ArrayList<>();

        FileWriter fileWriter = new FileWriter(testFilename);
        for (int i = 0; i < 1000000; i++) {
            fileWriter.write(template);
            expected.add(i * template.length);
        }

        fileWriter.close();

        FileInputStream fileInputStream = new FileInputStream(testFilename);

        TemplateIncludes templateIncludes = new TemplateIncludes(fileInputStream);
        List<Integer> resList = templateIncludes.findIncludes(template);

        Assertions.assertArrayEquals(expected.toArray(), resList.toArray());
    }

}