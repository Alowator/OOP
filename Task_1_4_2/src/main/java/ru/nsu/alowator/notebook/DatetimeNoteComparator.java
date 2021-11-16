package ru.nsu.alowator.notebook;

import java.util.Comparator;

public class DatetimeNoteComparator implements Comparator<Note> {

    @Override
    public int compare(Note o1, Note o2) {
        return o1.getDatetime().compareTo(o2.getDatetime());
    }
}

