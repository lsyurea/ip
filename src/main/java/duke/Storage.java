package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import duke.task.Task;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                Ui.printError(new DukeException(e.getMessage()));
            }
        }
    }

    public void saveTask(ArrayList<Task> tasks) {
        try {
            FileOutputStream fos = new FileOutputStream(this.filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
            fos.close();
        } catch (IOException e) {
            Ui.printError(new DukeException(e.getMessage()));
        }
    }

    public ArrayList<Task> loadTask() {
        try {
            FileInputStream fis = new FileInputStream(this.filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            ArrayList<Task> tasks = (ArrayList<Task>) ois.readObject();
            ois.close();
            fis.close();
            
            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<Task>();
        }
    } 
}