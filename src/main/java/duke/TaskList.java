package duke;

import java.io.Serializable;
import java.util.ArrayList;

class TaskList implements Serializable {
    private ArrayList<Task> tasks;

    /**
     * Creates a new task list.
     */
    TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Delete a task from the task list.
     *
     * @param taskId Task ID.
     * @return Task that was deleted.
     * @throws DukeException If ID is not found.
     */
    Task deleteTask(int taskId) throws DukeException {
        try {
            return this.tasks.remove(taskId - 1);
        } catch (Exception e) {
            throw new DukeException("Failed to find task.");
        }
    }

    /**
     * Mark a task as done.
     *
     * @param taskId Task ID.
     * @return Task that was marked done.
     * @throws DukeException If ID is not found.
     */
    Task markDone(int taskId) throws DukeException {
        try {
            Task task = this.tasks.get(taskId - 1);
            task.markAsDone();
            return task;
        } catch (Exception e) {
            throw new DukeException("Failed to find task.");
        }
    }

    /**
     * Add a task to the task list.
     *
     * @param task Task to be added.
     * @return Flag whether task was successfully added.
     */
    boolean addTask(Task task) {
        return this.tasks.add(task);
    }

    /**
     * Generates a numbered list of tasks matching a query.
     *
     * @param query Query string.
     * @return Numbered list of matching tasks.
     */
    String query(String query) {
        TaskList match = new TaskList();

        for (Task t : this.tasks) {
            if (t.getDescription().contains(query)) {
                match.addTask(t);
            }
        }

        return match.toString();
    }

    /**
     * Generates numbered list of all tasks in the task list.
     *
     * @return Numbered list of all tasks.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            output.append(String.format("%d.%s%n", i + 1, tasks.get(i).toString()));
        }
        return output.toString().trim();
    }
}
