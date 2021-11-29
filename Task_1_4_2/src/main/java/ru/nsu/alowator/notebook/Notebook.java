package ru.nsu.alowator.notebook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.nsu.alowator.timer.Timer;

import java.lang.reflect.Type;
import java.util.*;

public class Notebook {

    private final Timer timer;
    private final List<Note> notes;

    public Notebook(Timer timer) {
        this.timer = timer;
        this.notes = new ArrayList<>();
    }

    public Notebook(Timer timer, String jsonNotebookDump) {
        this.timer = timer;
        Type listOfNotes = new TypeToken<ArrayList<Note>>(){}.getType();
        List<Note> loadedNotes = new Gson().fromJson(jsonNotebookDump, listOfNotes);
        if (loadedNotes == null) {
            loadedNotes = new ArrayList<>();
        }
        this.notes = loadedNotes;
    }

    public void add(String name, String text) {
        Note note = new Note(name, text, timer.getCurrentDateTime());
        notes.add(note);
    }

    public void remove(String text) {
        notes.removeIf(note -> Objects.equals(note.getName(), text));
    }

    public List<Note> getNotesSortedByDate() {
        return notes.stream().sorted(Note.datetimeComparator()).toList();
    }

    public List<Note> getNotesSortedByDateBetweenContainsAnyOf(Date start, Date end, List<String> keywords) {
        return notes.stream()
                .filter(note -> note.getDatetime().after(start) && note.getDatetime().before(end))
                .filter(note -> note.containsAnyOf(keywords))
                .sorted(Note.datetimeComparator())
                .toList();
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this.notes);
    }

}
