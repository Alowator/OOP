package ru.nsu.alowator;

import java.lang.System;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TemplateIncludes {

    private final InputStream inputStream;
    private final int templateLengthMult = 15;

    public TemplateIncludes(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<Integer> findIncludes(char[] template) throws IOException {
        List<Integer> includesIndexes = new ArrayList<>();

        char[] zFuncText = new char[template.length * templateLengthMult];
        System.arraycopy(template, 0, zFuncText, 0, template.length);
        readTo(zFuncText, template.length);

        int indexOffset = 0;
        int[] zFuncValue;
        while (true) {
            zFuncValue = zFunction(zFuncText);
            for (int i = template.length; i < zFuncText.length - template.length + 1; i++) {
                if (zFuncValue[i] >= template.length) {
                    includesIndexes.add(i - template.length + indexOffset);
                }
                if (zFuncText[i] == '\0') {
                    return includesIndexes;
                }
            }
            System.arraycopy(zFuncText, zFuncText.length - template.length + 1, zFuncText, template.length, template.length - 1);
            readTo(zFuncText, 2 * template.length - 1);
            indexOffset += template.length * (templateLengthMult - 2) + 1;

        }
    }

    private int[] zFunction (char[] str) {
        int[] z = new int[str.length];
        int l = 0;
        int r = 0;
        for (int i = 1; i < str.length; i++) {
            if (i <= r)
                z[i] = Math.min(r - i + 1, z[i - l]);
            while (i + z[i] < str.length && str[z[i]] == str[i + z[i]])
                z[i]++;
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }

    private void readTo(char[] dest, int destPos) throws IOException {
        int bytesToRead = dest.length - destPos;
        String str = new String(inputStream.readNBytes(bytesToRead), StandardCharsets.UTF_8);
        System.arraycopy(str.toCharArray(), 0, dest, destPos, str.length());
        if (str.length() < bytesToRead) {
            dest[destPos + str.length()] = '\0';
        }
    }

}



