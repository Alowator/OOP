package ru.nsu.alowator.pizzeria;

import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseLoggingTest {
    String LOG_DIR = "logs";
    String LOG_FILENAME = LOG_DIR + "/" + "test.log";
    Logger log;
    Pizzeria pizzeria;

    public void assertLogContains(String content) throws IOException {
        FileReader fr = new FileReader(LOG_FILENAME);
        Scanner scanner = new Scanner(fr);
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();
        fr.close();

        assertTrue(text.contains(content));
    }

    @BeforeEach
    void initLogger() throws IOException {
        new File(LOG_DIR).mkdirs();
        Handler handler = new FileHandler(LOG_FILENAME, 1024 * 1024, 1, false);
        handler.setFormatter(new SimpleFormatter());
        log = Logger.getLogger("test");
        log.addHandler(handler);

        pizzeria = new Pizzeria(  3, log);
    }
}
