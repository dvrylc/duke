package duke;

import java.text.SimpleDateFormat;

public class Duke {
    // Configuration
    static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HHmm");

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new Duke instance.
     *
     * @param filePath Relative path of the save file.
     */
    private Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            this.ui.showException(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Creates a new Duke instance, no-argument version.
     */
    Duke() {
        this("data/duke.txt");
    }

    /**
     * Starts Duke's execution, used for command-line execution.
     */
    private void run() {
        System.out.println(this.ui.showWelcome());

        String input = this.ui.getInput();
        while (input != null) {
            String output = process(input);
            System.out.println(output);

            input = this.ui.getInput();
        }

        System.out.println(this.ui.showGoodbye());
        System.exit(0);
    }

    /**
     * Obtains output from Duke given an input string.
     *
     * @param input Input string
     * @return Duke output
     */
    String process(String input) {
        StringBuilder output = new StringBuilder();

        try {
            String command = Parser.extractCommand(input);

            switch (command) {
            case "bye": {
                output.append(this.ui.showGoodbye());
                System.exit(0);
                break;
            }

            case "list": {
                output.append(this.ui.showTasks(this.tasks.toString()));
                break;
            }

            case "done":
                // fallthrough
            case "delete": {
                int taskId = Integer.parseInt(Parser.extractId(input));

                if (command.equals("delete")) {
                    Task deletedTask = this.tasks.deleteTask(taskId);
                    output.append(this.ui.showTaskDeletion(deletedTask));
                }

                if (command.equals("done")) {
                    Task doneTask = this.tasks.markDone(taskId);
                    output.append(this.ui.showTaskDone(doneTask));
                }

                break;
            }

            case "find": {
                String query = Parser.extractQuery(input);
                output.append(this.ui.showQuery(this.tasks.query(query)));
                break;
            }

            case "todo":
                // fallthrough
            case "deadline":
                // fallthrough
            case "event": {
                Task newTask = Parser.parseTask(input);
                this.tasks.addTask(newTask);
                output.append(this.ui.showTaskAdded(newTask));
                break;
            }

            default: {
                output.append(this.ui.showException(new DukeException("Sorry I do not understand. Please try again.")));
            }
            }

            this.storage.persist(this.tasks);
        } catch (DukeException e) {
            output.append(this.ui.showException(e));
        }

        return output.toString();
    }

    /**
     * Instantiates and runs the a new Duke instance.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
