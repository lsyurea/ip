package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
    LocalDate date;
    
    public DeadlineTask(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + 
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}