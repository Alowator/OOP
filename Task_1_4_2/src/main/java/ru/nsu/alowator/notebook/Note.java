package ru.nsu.alowator.notebook;

import java.util.Date;

public class Note {

    private String name;
    private String text;
    private Date datetime;

    public Note(String name, String text, Date datetime) {
        this.name = name;
        this.text = text;
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public Date getDatetime() {
        return datetime;
    }

    public static DatetimeNoteComparator datetimeComparator() {
        return new DatetimeNoteComparator();
    };

}
