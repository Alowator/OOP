package ru.nsu.alowator.storage.impl.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BaseCollection {
    public Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    public String readFile(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            Scanner scanner = new Scanner(fr);
            String dump = scanner.useDelimiter("\\A").next();
            scanner.close();
            fr.close();
            return dump;
        } catch (IOException e) {
            return "[]";
        }
    }

    public void writeFile(String filename, String content) {
        try {
            FileWriter fw = new FileWriter(filename);
            fw.write(content);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write:");
            System.out.println(e.getMessage());
        }
    }
}
