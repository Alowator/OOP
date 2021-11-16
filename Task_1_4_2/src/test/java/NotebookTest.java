import ru.nsu.alowator.notebook.Notebook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.alowator.timer.Timer;

import java.util.Date;

class NotebookTest {

    Notebook notebook;

    class MockTimer implements Timer {

        private long DATE = 23490234;

        @Override
        public Date getCurrentDateTime() {
            Date date = new Date();
            date.setTime(DATE);
            return date;
        }
    }

    @BeforeEach
    void notebook() {
        MockTimer timer = new MockTimer();
        notebook = new Notebook(timer);
    }

    @Test
    void add_single() {
        notebook.add("vary useful1");
    }

    @Test
    void add_multi() {
        notebook.add("vary useful1");
        notebook.add("Nastya gay");
        notebook.add("Real gey");
    }

    @Test
    void remove() {
        notebook.add("vary useful1");
        notebook.remove("vary useful1");
    }

}