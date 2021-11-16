import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.alowator.notebook.Note;
import ru.nsu.alowator.notebook.Notebook;
import ru.nsu.alowator.timer.Timer;

import java.util.Date;
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
        notebook.add("vary useful1");
        notebook.add("roflokek");

        List<Note> notes = notebook.getNotesSortedByDate();

        Assertions.assertEquals("vary useful1", notes.get(0).getText());
        Assertions.assertEquals(timer.time - 1, notes.get(0).getDatetime().getTime());
        Assertions.assertEquals("roflokek", notes.get(1).getText());
        Assertions.assertEquals(timer.time, notes.get(1).getDatetime().getTime());
    }

    @Test
    void remove() {
        notebook.add("vary useful1");
        notebook.remove("vary useful1");

        List<Note> notes = notebook.getNotesSortedByDate();

        Assertions.assertEquals(0, notes.size());
    }

}