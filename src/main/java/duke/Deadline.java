package duke;

import java.util.Date;

class Deadline extends Task {
    private Date by;

    /**
     * Creates a new deadline task.
     *
     * @param description Description of the task.
     * @param by          Deadline of the task.
     */
    Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Generates the task's text representation in display format.
     *
     * @return Text representation of the task in display format.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Duke.DATE_FORMATTER.format(this.by));
    }
}
