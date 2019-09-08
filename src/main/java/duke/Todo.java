package duke;

class Todo extends Task {
    /**
     * Creates a new todo.
     *
     * @param description Description of the todo.
     */
    Todo(String description) {
        super(description);
    }

    /**
     * Generates the task's text representation in display format.
     *
     * @return Text representation of the task in display format
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
