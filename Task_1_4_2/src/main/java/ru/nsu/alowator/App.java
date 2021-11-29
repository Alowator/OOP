package ru.nsu.alowator;


import ru.nsu.alowator.notebook.Note;
import ru.nsu.alowator.notebook.Notebook;
import ru.nsu.alowator.timer.RealTimer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {

    private static class Args {
        String title = NB_DEFAULT_NAME;
        String cmd;
        List<String> args = new ArrayList<>();
    }

    private static final String NB_DEFAULT_NAME = "notebook";
    private static final String NB_EXTENSION = ".nb";
    private static final String INFO_MESSAGE = """
            Use:
                [-title <title>] -add <note_name> <note_text>
                [-title <title>] -rm <note_name>
                [-title <title>] -show
                [-title <title>] -show <from_date> <to_date> <keywords>...
                
            """;
    private static final String INCORRECT_DATE_MESSAGE = """
            Incorrect date format, use: dd.mm.yyyy HH:MM
            """;

    public static void main(String[] args) {
        Args parsedArgs;
        try {
            parsedArgs = parseArgs(args);
        } catch (ParseException e) {
            System.out.println(INFO_MESSAGE);
            return;
        }

        Notebook notebook = readNotebook(parsedArgs.title);
        if (parsedArgs.cmd == null) {
            System.out.println(INFO_MESSAGE);
            return;
        }

        if (parsedArgs.cmd.contains("add")) {
            notebook.add(parsedArgs.args.get(0), parsedArgs.args.get(1));
        } else if (parsedArgs.cmd.contains("rm")) {
            notebook.remove(parsedArgs.args.get(0));
        } else if (parsedArgs.cmd.contains("show")) {
            List<Note> notes;
            if (parsedArgs.args.size() == 0) {
                notes = notebook.getNotesSortedByDate();
            } else {
                try {
                    notes = notebook.getNotesSortedByDateBetweenContainsAnyOf(
                            parseDate(parsedArgs.args.get(0)),
                            parseDate(parsedArgs.args.get(1)),
                            parsedArgs.args.subList(2, parsedArgs.args.size())
                    );
                } catch (ParseException e) {
                    System.out.println(INCORRECT_DATE_MESSAGE);
                    return;
                }
            }
            for (Note note : notes) {
                System.out.println(note);
            }
        } else {
            System.out.println(INFO_MESSAGE);
            return;
        }

        writeNotebook(notebook, parsedArgs.title);
    }

    private static Notebook readNotebook(String notebookTitle) {
        Notebook notebook;
        try {
            FileReader fr = new FileReader(notebookTitle + NB_EXTENSION);
            Scanner scanner = new Scanner(fr);
            String notebookDump = scanner.useDelimiter("\\A").next();
            notebook = new Notebook(new RealTimer(), notebookDump);
            scanner.close();
        } catch (FileNotFoundException e) {
            notebook = new Notebook(new RealTimer());
        }
        return notebook;
    }

    private static void writeNotebook(Notebook notebook, String notebookTitle) {
        try {
            FileWriter fw = new FileWriter(notebookTitle + NB_EXTENSION);
            String dump = notebook.toJson();
            fw.write(dump);
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write:");
            System.out.println(e.getMessage());
        }
    }

    private static Args parseArgs(String[] args) throws ParseException {
        Args parsedArgs;
        try {
            parsedArgs = new Args();
            Iterator<String> it = Arrays.stream(args).iterator();
            while (it.hasNext()) {
                String cmd = it.next();
                if (Objects.equals(cmd, "-add")) {
                    parsedArgs.cmd = "-add";
                    parsedArgs.args.add(it.next());
                    parsedArgs.args.add(it.next());
                    break;
                } else if (Objects.equals(cmd, "-rm")) {
                    parsedArgs.cmd = "-rm";
                    parsedArgs.args.add(it.next());
                    break;
                } else if (Objects.equals(cmd, "-show")) {
                    parsedArgs.cmd = "-show";
                    while (it.hasNext()) {
                        parsedArgs.args.add(it.next());
                    }
                    if (parsedArgs.args.size() == 1) {
                        throw new ParseException("Not enough args in -show", 0);
                    }
                } else if (Objects.equals(cmd, "-title")) {
                    parsedArgs.title = it.next();
                }
            }
        } catch (Exception e) {
            throw new ParseException(e.getMessage(), 0);
        }
        return parsedArgs;
    }

    private static Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy kk:mm");
        return formatter.parse(dateString);
    }

}
