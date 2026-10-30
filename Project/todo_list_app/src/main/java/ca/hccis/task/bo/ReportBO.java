package ca.hccis.task.bo;

import ca.hccis.task.dao.TaskDao;
import ca.hccis.task.entity.ReportInput;
import ca.hccis.task.entity.TaskCategorySummary;

import java.util.ArrayList;

/**
 * Class to hold business logic needed for the report.
 *
 * @author Yasir Al Muhib
 * @since 20261026
 */
public class ReportBO {

    public static void processTaskCategoryReport(ReportInput reportInput) {

        // Get the task counts by category
        TaskDao taskDao = new TaskDao();
        ArrayList<TaskCategorySummary> taskCategorySummaries =
                taskDao.selectTaskCountByCategory(reportInput.getStatus());

        // Set the report results
        reportInput.setTaskCategorySummaries(taskCategorySummaries);
    }
}
