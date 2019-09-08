package duke;

import java.util.Date;

class Event extends Task {
    private Date at;

    /**
     * Creates a new event task.
     *
     * @param description Description of the event.
     * @param at          Date/time of the event.
     */
    Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * Generates the task's text representation in display format.
     *
     * @return Textual Text of the task in display format.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (by: %s)", super.toString(), Duke.DATE_FORMATTER.format(this.at));
    }
}
