package ca.hccis.multithreading.entity;

import ca.hccis.util.CisUtility;
import javax.swing.JOptionPane;

/**
 * Represents a task in the To-Do List Application.
 *
 * @author yam
 * @since 20260923
 */
public class Task {

    private int id;
    private String taskName;
    private String description;
    private String dueDate;
    private int estimatedTime;
    private String status;
    private String notes;
    private String category;
    private String priority;

    //Default Constructor
    public Task() {
    }

    //Custom Constructor
    public Task(int id, String taskName, String description, String dueDate,
                int estimatedTime, String status, String notes,
                String category, String priority) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;
        this.estimatedTime = estimatedTime;
        this.status = status;
        this.notes = notes;
        this.category = category;
        this.priority = priority;
    }

    /**
     * Gets information from the user to create a new task.
     *
     * @author yam
     * @since 20260923
     */
    public void getInformation() {
        taskName = CisUtility.getInputString("Task Name: ");

        description = CisUtility.getInputString("Description: ");

        dueDate = CisUtility.getInputString("Due Date: ");

        estimatedTime = CisUtility.getInputInt("Estimated Time (minutes): ");

        status = CisUtility.getInputString("Status (done/undone): ");

        notes = CisUtility.getInputString("Notes: ");

        category = CisUtility.getInputString("Category: ");

        priority = CisUtility.getInputString("Priority (High/Medium/Low): ");
    }


    public void getInformationGUI() {

        taskName = JOptionPane.showInputDialog("Task Name:");
        if (taskName == null) {
            throw new java.util.concurrent.CancellationException();
        }

        description = JOptionPane.showInputDialog("Description:");
        if (description == null) {
            throw new java.util.concurrent.CancellationException();
        }

        dueDate = JOptionPane.showInputDialog("Due Date:");
        if (dueDate == null) {
            throw new java.util.concurrent.CancellationException();
        }

        while (true) {
            String time = JOptionPane.showInputDialog(
                    "Estimated Time (minutes):");

            if (time == null) {
                throw new java.util.concurrent.CancellationException();
            }

            try {
                estimatedTime = Integer.parseInt(time);

                if (estimatedTime >= 0) {
                    break;
                }
            } catch (NumberFormatException e) {
                // Ask again for a valid number.
            }

            JOptionPane.showMessageDialog(
                    null, "Enter a valid non-negative integer.");
        }

        status = JOptionPane.showInputDialog("Status (done/undone):");
        if (status == null) {
            throw new java.util.concurrent.CancellationException();
        }

        notes = JOptionPane.showInputDialog("Notes:");
        if (notes == null) {
            throw new java.util.concurrent.CancellationException();
        }

        category = JOptionPane.showInputDialog("Category:");
        if (category == null) {
            throw new java.util.concurrent.CancellationException();
        }

        priority = JOptionPane.showInputDialog(
                "Priority (High/Medium/Low):");
        if (priority == null) {
            throw new java.util.concurrent.CancellationException();
        }
    }

    // --- Getters & Setters ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Returns the task information as a String.
     *
     * @return task information
     * @author yam
     * @since 20260923
     */
    @Override
    public String toString() {
        return String.format(
                "Task: id=%d, taskName='%s', description='%s', dueDate='%s', " +
                        "estimatedTime=%d, status='%s', notes='%s', category='%s', priority='%s'",
                id, taskName, description, dueDate, estimatedTime,
                status, notes, category, priority
        );
    }
}
