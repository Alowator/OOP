package ru.nsu.alowator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TemplateIncludesTest {

    String testFilename = "input_test.txt";

    void writeTestFile(String text) throws IOException {
        FileWriter fileWriter = new FileWriter(testFilename);
        fileWriter.write(text);
        fileWriter.close();

    }

    @Test
    void findIncludes_empty() throws IOException {
        char[] templateArray = new char[]{1087, 1080, 1088, 1086, 1075};
        String template = new String(templateArray);
        String text = "Я хочу пирок!";
        Integer[] expected = {};

        writeTestFile(text);

        TemplateIncludes templateIncludes = new TemplateIncludes(testFilename);
        List<Integer> resList = templateIncludes.findIncludes(template);

        Assertions.assertArrayEquals(expected, resList.toArray());
    }

    @Test
    void findIncludes_sample() throws IOException {
        char[] templateArray = new char[]{1087, 1080, 1088, 1086, 1075};
        String template = new String(templateArray);
        String text = "Я хочу пирог!";
        Integer[] expected = {7};

        writeTestFile(text);

        TemplateIncludes templateIncludes = new TemplateIncludes(testFilename);
        List<Integer> resList = templateIncludes.findIncludes(template);

        Assertions.assertArrayEquals(expected, resList.toArray());
    }

    @Test
    void findIncludes_twice() throws IOException {
        char[] templateArray = new char[]{1087, 1080, 1088, 1086, 1075};
        String template = new String(templateArray);
        String text = "Я хочу пирог, пирог хочу!";
        Integer[] expected = {7, 14};

        writeTestFile(text);

        TemplateIncludes templateIncludes = new TemplateIncludes(testFilename);
        List<Integer> resList = templateIncludes.findIncludes(template);

        Assertions.assertArrayEquals(expected, resList.toArray());
    }

    @Test
    void findIncludes_latin() throws IOException {
        String template = "aba";
        String text = "abacabaCAba";
        Integer[] expected = {0, 4};

        writeTestFile(text);

        TemplateIncludes templateIncludes = new TemplateIncludes(testFilename);
        List<Integer> resList = templateIncludes.findIncludes(template);

        Assertions.assertArrayEquals(expected, resList.toArray());
    }

    @Test
    void findIncludes_intersection() throws IOException {
        String template = "lol";
        String text = "lololol";
        Integer[] expected = {0, 2, 4};

        writeTestFile(text);

        TemplateIncludes templateIncludes = new TemplateIncludes(testFilename);
        List<Integer> resList = templateIncludes.findIncludes(template);

        Assertions.assertArrayEquals(expected, resList.toArray());
    }

    @Test
    void findIncludes_extraSymbols() throws IOException {
        String template = " `; a;d,;/234=-";
        String text = "   `; a;d,;/234=-";
        Integer[] expected = {2};

        writeTestFile(text);

        TemplateIncludes templateIncludes = new TemplateIncludes(testFilename);
        List<Integer> resList = templateIncludes.findIncludes(template);

        Assertions.assertArrayEquals(expected, resList.toArray());
    }

    @Test
    void findIncludes_bigText() throws IOException {
        String template = "very big text, and this is interesting kek lul!!!";
        List<Integer> expected = new ArrayList<>();

        FileWriter fileWriter = new FileWriter(testFilename);
        for (int i = 0; i < 1000000; i++) {
            fileWriter.write(template);
            expected.add(i * template.length());
        }

        fileWriter.close();

        TemplateIncludes templateIncludes = new TemplateIncludes(testFilename);
        List<Integer> resList = templateIncludes.findIncludes(template);

        Assertions.assertArrayEquals(expected.toArray(), resList.toArray());
    }

}