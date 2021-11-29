package ru.nsu.alowator.notebook;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    public boolean containsAnyOf(List<String> keywords) {
        keywords = keywords.stream().map(String::toLowerCase).toList();
        String lowerName = name.toLowerCase();
        String lowerText = text.toLowerCase();

        for (String keyword : keywords) {
            if (lowerText.contains(keyword) || lowerName.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    public static DatetimeNoteComparator datetimeComparator() {
        return new DatetimeNoteComparator();
    };

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy kk:mm");
        return name + " (" + formatter.format(datetime) + "):\n" + text;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Note note = (Note) obj;
        return Objects.equals(name, note.name) &&
                Objects.equals(text, note.text) &&
                Objects.equals(datetime.toString(), note.datetime.toString());
    }

}
