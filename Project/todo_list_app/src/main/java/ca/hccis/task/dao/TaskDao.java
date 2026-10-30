package ca.hccis.task.dao;

import ca.hccis.task.entity.TaskCategorySummary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Class to access task report data.
 *
 * @author Yasir Al Muhib
 * @since 20261026
 */
public class TaskDao {

    private static ResultSet rs;
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(TaskDao.class);

    public TaskDao() {

        String propFileName = "application";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String connectionString = rb.getString("spring.datasource.url");
        String userName = rb.getString("spring.datasource.username");
        String password = rb.getString("spring.datasource.password");

        try {
            conn = DriverManager.getConnection(connectionString, userName, password);
        } catch (SQLException e) {
            logger.error(e.toString());
        }
    }
    /**
     * Select task counts grouped by category for the selected status.
     *
     * @since 20261026
     * @author Yasir Al Muhib
     */
    public ArrayList<TaskCategorySummary> selectTaskCountByCategory(String status) {

        ArrayList<TaskCategorySummary> taskCategorySummaries = new ArrayList<>();
        PreparedStatement stmt = null;

        try {

            String sqlStatement = "SELECT category, COUNT(*) AS taskCount " +
                    "FROM task " +
                    "WHERE status = ? " +
                    "GROUP BY category";

            stmt = conn.prepareStatement(sqlStatement);
            stmt.setString(1, status);

            rs = stmt.executeQuery();

            while (rs.next()) {

                TaskCategorySummary current = new TaskCategorySummary();
                current.setCategory(rs.getString("category"));
                current.setTaskCount(rs.getInt("taskCount"));

                taskCategorySummaries.add(current);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("There was an error closing");
            }
        }

        return taskCategorySummaries;
    }
}
