package ru.nsu.alowator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TemplateIncludes {

    private String template;
    private BufferedReader bufferedReader;

    private List<Integer> includesIndexes;

    public TemplateIncludes(String template, FileReader fileReader) {
        this.template = template;
        this.bufferedReader = new BufferedReader(fileReader);

        this.includesIndexes = new ArrayList<>();
        findIncludes();
    }

    private void findIncludes() {
        includesIndexes.clear();
        LinkedList<Character> text = new LinkedList<>();
        LinkedList<Integer> textZ = new LinkedList<>();
        int[] templateZ = zFunction(template);

        int symbol;
        for (int i = 0; i < template.length(); i++) {
            symbol = getNextChar();
            if (symbol == -1)
                return;
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
                includesIndexes.add(i / 2);

            symbol = getNextChar();
            if (symbol == -1)
                return;
            text.removeFirst();
            text.addLast((char) symbol);
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

    public List<Integer> getIncludesIndexes() {
        return includesIndexes;
    }

}



