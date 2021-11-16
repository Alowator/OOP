package ru.nsu.alowator.notebook;

import java.util.Date;

public class Note {

    private String text;
    private Date datetime;

    public Note(String text, Date datetime) {
        this.text = text;
        this.datetime = datetime;
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
