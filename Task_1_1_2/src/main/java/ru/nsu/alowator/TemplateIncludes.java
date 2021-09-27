package ru.nsu.alowator;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class TemplateIncludes {

    private String filename;
    private BufferedReader bufferedReader;

    public TemplateIncludes(String filename) {
        this.filename = filename;
    }

    public List<Integer> findIncludes(String template) throws IOException {
        bufferedReader = Files.newBufferedReader(Path.of(filename), StandardCharsets.UTF_8);
        List<Integer> includesIndexes = new ArrayList<>();
        LinkedList<Character> text = new LinkedList<>();
        LinkedList<Integer> textZ = new LinkedList<>();
        int[] templateZ = zFunction(template);

        int symbol;
        for (int i = 0; i < template.length(); i++) {
            symbol = getNextChar();
            if (symbol == -1)
                return includesIndexes;
            text.add((char) symbol);
            textZ.add(0);
        }

        int i = 0, l = 0, r = 0;
        while (true) {
            if (i <= r)
                textZ.set(0, Math.min(r - i + 1, templateZ[i - l]));
            while (textZ.get(0) < text.size() && template.charAt(textZ.get(0)) == text.get(textZ.get(0)))
                textZ.set(0, textZ.get(0) + 1);
            if (i + textZ.get(0) - 1 > r) {
                l = i;
                r = i + textZ.get(0) - 1;
            }

            if (textZ.get(0) == template.length())
                includesIndexes.add(i);

            symbol = getNextChar();
            if (symbol == -1)
                return includesIndexes;
            text.removeFirst();
            text.addLast((char) symbol);
            textZ.removeFirst();
            textZ.addLast(0);
            i++;
        }
    }

    private int[] zFunction (String str) {
        int[] z = new int[str.length()];
        for (int i = 1, l = 0, r = 0; i < str.length(); i++) {
            if (i <= r)
                z[i] = Math.min(r - i + 1, z[i - l]);
            while (i + z[i] < str.length() && str.charAt(z[i]) == str.charAt(i + z[i]))
                z[i]++;
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }

    private int getNextChar() {
        int symbol;
        try {
            symbol = bufferedReader.read();
        } catch (IOException e) {
            e.printStackTrace();
            symbol = -1;
        }
        return symbol;
    }

}



