
package ca.hccis.task.entity;

import ca.hccis.task.jpa.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDto {

    private List<Task> tasks;

    // default and parameterized constructor

    public TaskDto() {
        tasks = new ArrayList<Task>();
    }

    public TaskDto(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
