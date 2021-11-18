import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.alowator.notebook.Note;
import ru.nsu.alowator.notebook.Notebook;
import ru.nsu.alowator.timer.Timer;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


class NotebookTest {

    class MockTimer implements Timer {

        public long time = 23490234;

        @Override
        public Date getCurrentDateTime() {
            Date date = new Date();
            time += 1;
            date.setTime(time);
            return date;
        }
    }

    Notebook notebook;
    MockTimer timer;

    @BeforeEach
    void notebook() {
        timer = new MockTimer();
        notebook = new Notebook(timer);
    }

    @Test
    void add() {
        List<List<String>> values = new ArrayList<>();
        values.add(List.of("Task1", "Complete OOP homework"));
        values.add(List.of("Task2", "Sleep"));
        for (List<String> value : values) {
            notebook.add(value.get(0), value.get(1));
        }

        List<Note> notes = notebook.getNotesSortedByDate();

        for (int i = 0; i < values.size(); i++) {
            Assertions.assertEquals(values.get(i).get(0), notes.get(i).getName());
            Assertions.assertEquals(values.get(i).get(1), notes.get(i).getText());
            Assertions.assertEquals(
                    timer.time - values.size() + i + 1,
                    notes.get(i).getDatetime().getTime()
            );
        }
    }

    @Test
    void remove() {
        notebook.add("Task1", "Complete OOP homework");
        notebook.remove("Task1");

        List<Note> notes = notebook.getNotesSortedByDate();

        Assertions.assertEquals(0, notes.size());
    }

}