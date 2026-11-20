
package ca.hccis.task.jpa.entity;

import ca.hccis.task.util.CisUtility;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "task")
public class Task {

    public Task() {
        this.taskName = "";
        this.description = "";
        this.setDueDate(CisUtility.getCurrentDate("yyyy-MM-dd"));
        this.estimatedTime = 0;
        this.status = "";
        this.notes = "";
        this.category = "";
        this.priority = "";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Task name is required")
    @Size(max = 100)
    @Column(name = "taskName", nullable = false, length = 100)
    private String taskName;

    @NotBlank(message = "Description is required")
    @Size(max = 500)
    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @NotBlank(message = "Due date is required")
    @Size(max = 10)
    @Column(name = "dueDate", nullable = false, length = 10)
    private String dueDate;

    @NotNull(message = "Estimated time is required")
    @Min(value = 0, message = "Estimated time cannot be negative")
    @ColumnDefault("0")
    @Column(name = "estimatedTime", nullable = false)
    private Integer estimatedTime;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "(?i)Done|Undone",
            message = "Status must be Done or Undone")
    @Size(max = 30)
    @Column(name = "status", nullable = false, length = 30)
    private String status;

    @Size(max = 500)
    @Column(name = "notes", length = 500)
    private String notes;

    @NotBlank(message = "Category is required")
    @Pattern(regexp = "(?i)School|Work|Personal",
            message = "Category must be School, Work, or Personal")
    @Size(max = 50)
    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @NotBlank(message = "Priority is required")
    @Pattern(regexp = "(?i)High|Medium|Low",
            message = "Priority must be High, Medium, or Low")
    @Size(max = 20)
    @Column(name = "priority", nullable = false, length = 20)
    private String priority;

    @Column(name = "priorityScore", nullable = false)
    private Integer priorityScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
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

    public Integer getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(Integer priorityScore) {
        this.priorityScore = priorityScore;
    }
}
