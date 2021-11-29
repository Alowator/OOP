import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.alowator.notebook.Note;
import ru.nsu.alowator.notebook.Notebook;
import ru.nsu.alowator.timer.Timer;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class NotebookTest {

    class MockTimer implements Timer {

        public long time = 23490234;

        @Override
        public Date getCurrentDateTime() {
            Date date = new Date();
            date.setTime(time);
            return date;
        }
    }

    Notebook notebook;
    MockTimer timer;
    List<List<String>> noteTemplates;

    @BeforeEach
    void notebook() {
        timer = new MockTimer();
        notebook = new Notebook(timer);

        noteTemplates = new ArrayList<>();
        noteTemplates.add(List.of("Task1", "Complete OOP homework"));
        noteTemplates.add(List.of("Task2", "Sleep"));
        noteTemplates.add(List.of("Task3", "Work hard"));
        noteTemplates.add(List.of("lul", "kek"));
    }

    @Test
    void add() {
        for (List<String> template : noteTemplates) {
            notebook.add(template.get(0), template.get(1));
            timer.time += 1;
        }

        List<Note> notes = notebook.getNotesSortedByDate();

        for (int i = 0; i < noteTemplates.size(); i++) {
            Assertions.assertEquals(noteTemplates.get(i).get(0), notes.get(i).getName());
            Assertions.assertEquals(noteTemplates.get(i).get(1), notes.get(i).getText());
            Assertions.assertEquals(
                    timer.time - noteTemplates.size() + i,
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

    @Test
    void getNotesSortedByDate() {
        for (List<String> value : noteTemplates) {
            notebook.add(value.get(0), value.get(1));
            timer.time -= 1;
        }

        List<Note> notes = notebook.getNotesSortedByDate();

        for (int i = 0; i < noteTemplates.size(); i++) {
            Assertions.assertEquals(
                    noteTemplates.get(noteTemplates.size() - i - 1).get(0),
                    notes.get(i).getName()
            );
        }
    }

    @Test
    void getNotesSortedByDateBetween() {
        for (List<String> value : noteTemplates) {
            timer.time += 1;
            notebook.add(value.get(0), value.get(1));
        }

        List<String> keywords = new ArrayList<>();
        keywords.add("sleep");
        keywords.add("task3");
        List<Note> notes = notebook.getNotesSortedByDateBetweenContainsAnyOf(
                new Date(timer.time - 2),
                new Date(timer.time + 1),
                keywords
        );

        Assertions.assertEquals(
                noteTemplates.get(2).get(0),
                notes.get(0).getName()
        );
    }

    @Test
    void toJson() {
        for (List<String> template : noteTemplates) {
            notebook.add(template.get(0), template.get(1));
            timer.time += 1;
        }

        List<Note> notes = notebook.getNotesSortedByDate();

        String jsonNotebook = notebook.toJson();
        System.out.println(jsonNotebook);

        Notebook newNotebook = new Notebook(timer, jsonNotebook);
        System.out.println(newNotebook.toJson());

    }


}