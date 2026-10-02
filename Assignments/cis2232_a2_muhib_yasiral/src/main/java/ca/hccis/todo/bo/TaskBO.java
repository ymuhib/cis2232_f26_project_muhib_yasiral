package ca.hccis.todo.bo;

import ca.hccis.todo.entity.Task;

/**
 * Business object for performing calculations related to tasks.
 *
 * @author yam
 * @since 20261002
 */
public class TaskBO {

    /**
     * Calculates the estimated time remaining for a task.
     * An undone task contributes its estimated time.
     * A done task contributes zero minutes.
     *
     * @param task task used for the calculation
     * @return estimated time remaining in minutes
     * @author yam
     * @since 20261002
     */
    public double calculate(Task task) {

        if (task.getStatus().equalsIgnoreCase("undone")) {
            return task.getEstimatedTime();
        }

        return 0;
    }
}
