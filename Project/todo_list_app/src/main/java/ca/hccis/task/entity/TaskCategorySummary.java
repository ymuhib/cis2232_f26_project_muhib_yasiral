package ca.hccis.task.entity;

/**
 * Class to hold task category report summary information.
 *
 * @author Yasir Al Muhib
 * @since 20261025
 */
public class TaskCategorySummary {

    private String category;
    private int taskCount;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }
    @Override
    public String toString() {
        return category + " - " + taskCount;
    }
}