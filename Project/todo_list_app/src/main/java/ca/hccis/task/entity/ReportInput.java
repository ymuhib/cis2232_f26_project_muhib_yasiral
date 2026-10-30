package ca.hccis.task.entity;

import java.util.ArrayList;

/**
 * Entity class to hold the attributes of the reports.
 *
 * @author Yasir Al Muhib
 * @since 20261028
 */
public class ReportInput {

    private String status;
    private ArrayList<TaskCategorySummary> taskCategorySummaries;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<TaskCategorySummary> getTaskCategorySummaries() {
        return taskCategorySummaries;
    }

    public void setTaskCategorySummaries(ArrayList<TaskCategorySummary> taskCategorySummaries) {
        this.taskCategorySummaries = taskCategorySummaries;
    }
}
