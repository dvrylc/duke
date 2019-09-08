package duke;

import java.text.ParseException;
import java.util.Date;

public class Parser {
    /**
     * Extracts the command from an input string.
     *
     * @param input Input string.
     * @return Command.
     */
    static String extractCommand(String input) {
        return input.split(" ", 2)[0];
    }

    /**
     * Extracts the item's id from an input string.
     *
     * @param input Input string.
     * @return id.
     */
    static String extractId(String input) {
        return input.split(" ", 2)[1];
    }

    /**
     * Extracts the query from an input string.
     *
     * @param input Input string.
     * @return Query.
     */
    static String extractQuery(String input) {
        return Parser.extractId(input);
    }

    /**
     * Parses an input string into a task.
     *
     * @param input Input string.
     * @return Task.
     * @throws DukeException If date is invalid.
     */
    static Task parseTask(String input) throws DukeException {
        String[] tokens = input.split(" ", 2);
        String command = tokens[0];
        String data = tokens[1];

        Task task;

        if (command.equals("todo")) {
            task = new Todo(data);
            return task;
        }

        if (command.equals("deadline")) {
            tokens = data.split(" /by ");
        } else {
            tokens = data.split(" /at ");
        }

        String description = tokens[0];
        String dateStr = tokens[1];
        Date date = Parser.parseDate(dateStr);

        if (command.equals("deadline")) {
            task = new Deadline(description, date);
        } else {
            task = new Event(description, date);
        }
        return task;
    }

    /**
     * Parses a date string into a Date object.
     *
     * @param dateStr Date string.
     * @return Date object.
     * @throws DukeException If date string is invalid.
     */
    static Date parseDate(String dateStr) throws DukeException {
        try {
            return Duke.DATE_FORMATTER.parse(dateStr);
        } catch (ParseException e) {
            throw new DukeException("Failed to parse date.");
        }
    }
}
