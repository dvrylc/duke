package duke;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Storage {
    private String filePath;

    /**
     * Creates a new Storage instance.
     *
     * @param filePath Relative path of the save file.
     */
    Storage(String filePath) {
        this.filePath = String.format("%s/%s", System.getProperty("user.dir"), filePath);
    }

    /**
     * Loads tasks from a save file.
     *
     * @return TaskList with all saved tasks.
     * @throws DukeException If file I/O fails or if save file data is invalid.
     */
    TaskList load() throws DukeException {
        try {
            FileInputStream fin = new FileInputStream(this.filePath);
            ObjectInputStream ois = new ObjectInputStream(fin);

            return (TaskList) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeException("Failed to read from save file.");
        }
    }

    /**
     * Writes tasks to save file.
     *
     * @param tasks TaskList with current tasks.
     * @throws DukeException If file I/O fails.
     */
    void persist(TaskList tasks) throws DukeException {
        try {
            FileOutputStream fout = new FileOutputStream(this.filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            oos.writeObject(tasks);
            fout.close();
        } catch (IOException e) {
            throw new DukeException("Failed to write to save file.");
        }
    }
}
