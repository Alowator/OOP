package ru.nsu.alowator.notebook;

import ru.nsu.alowator.timer.Timer;

import java.util.*;

public class Notebook {

    private Timer timer;
    private List<Note> notes;

    public Notebook(Timer timer) {
        this.timer = timer;
        this.notes = new ArrayList<>();
    }

    public Note add(String text) {
        Note note = new Note(text, timer.getCurrentDateTime());
        notes.add(note);
        return note;
    }

    public boolean remove(String text) {
        return notes.removeIf(note -> Objects.equals(note.getText(), text));
    }

    public List<Note> getNotesSortedByDate() {
        return notes.stream().sorted(Note.datetimeComparator()).toList();
    }

    public List<Note> getNotesSortedByDateBetween(Date start, Date end) {
        return notes.stream()
                .filter(note -> note.getDatetime().after(start) && note.getDatetime().before(end))
                .sorted(Note.datetimeComparator())
                .toList();
    }

}
