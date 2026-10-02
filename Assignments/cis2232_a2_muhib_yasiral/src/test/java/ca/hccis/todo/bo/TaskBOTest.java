package ca.hccis.todo.bo;

import ca.hccis.todo.entity.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskBOTest {

    /**
     * Test 1 created following a TDD approach.
     * This test verifies that the estimated time is returned
     * when a task is unfinished.
     *
     * @since 20261002
     * @author yam
     */
    @Test
    void testCalculateUndoneTask() {

        Task task = new Task();

        task.setEstimatedTime(60);
        task.setStatus("undone");

        TaskBO taskBO = new TaskBO();

        double actual = taskBO.calculate(task);

        assertEquals(60, actual);
    }

    /**
     * Test 2 created following a TDD approach.
     *
     * This test verifies that a completed task contributes
     * zero minutes to the remaining estimated time.
     *
     * @since 20261002
     * @author yam
     */
    @Test
    void testCalculateDoneTask() {

        Task task = new Task();

        task.setEstimatedTime(45);
        task.setStatus("done");

        TaskBO taskBO = new TaskBO();

        double actual = taskBO.calculate(task);

        assertEquals(0, actual);
    }

    /**
     * Test 3 created following a TDD approach.
     *
     * This test verifies that the calculation recognizes
     * an unfinished status regardless of letter case.
     *
     * @since 20261002
     * @author yam
     */
    @Test
    void testCalculateUndoneTaskUppercaseStatus() {

        Task task = new Task();

        task.setEstimatedTime(30);
        task.setStatus("UNDONE");

        TaskBO taskBO = new TaskBO();

        double actual = taskBO.calculate(task);

        assertEquals(30, actual);
        assertTrue(actual > 0);
    }


    //****************************************************************************
    // The following are unit tests created by AI
    //****************************************************************************

    /**
     * Tests an unfinished real-life task.
     */
    @Test
    void calculate_groceryShopping() {

        Task task = new Task(
                1,
                "Grocery Shopping",
                "Buy groceries for the week",
                "2026-10-05",
                60,
                "undone",
                "Buy milk, eggs, bread, and vegetables",
                "Personal",
                "Medium"
        );

        TaskBO taskBO = new TaskBO();

        assertEquals(60, taskBO.calculate(task));
    }

    /**
     * Tests a completed real-life electricity bill task.
     */
    @Test
    void calculate_completedElectricityBill() {

        Task task = new Task(
                2,
                "Pay Electricity Bill",
                "Pay the monthly electricity bill",
                "2026-10-06",
                20,
                "done",
                "Pay the bill online",
                "Personal",
                "High"
        );

        TaskBO taskBO = new TaskBO();

        assertEquals(0, taskBO.calculate(task));
    }

    /**
     * Tests an unfinished real-life apartment cleaning task.
     */
    @Test
    void calculate_cleanApartment() {

        Task task = new Task(
                3,
                "Clean the Apartment",
                "Clean and organize the apartment",
                "2026-10-07",
                120,
                "undone",
                "Clean the kitchen, bathroom, and living room",
                "Personal",
                "Medium"
        );

        TaskBO taskBO = new TaskBO();

        double actual = taskBO.calculate(task);

        assertEquals(120, actual);
        assertTrue(actual > 0);
    }

    /**
     * Tests that an unfinished task with zero estimated time
     * returns zero.
     */
    @Test
    void calculate_zeroEstimatedTime() {

        Task task = new Task(
                4,
                "Check Mail",
                "Check the mailbox",
                "2026-10-08",
                0,
                "undone",
                "Check for new mail",
                "Personal",
                "Low"
        );

        TaskBO taskBO = new TaskBO();

        double actual = taskBO.calculate(task);

        assertEquals(0, actual);
    }

    /**
     * Tests that the calculation works with a lowercase undone status.
     */
    @Test
    void calculate_lowercaseUndoneStatus() {

        Task task = new Task(
                5,
                "Walk the Dog",
                "Take the dog for a walk",
                "2026-10-08",
                30,
                "undone",
                "Walk around the neighbourhood",
                "Personal",
                "Low"
        );

        TaskBO taskBO = new TaskBO();

        double actual = taskBO.calculate(task);

        assertEquals(30, actual);
        assertTrue(actual > 0);
    }

    /**
     * Tests that the calculation works with mixed-case undone status.
     */
    @Test
    void calculate_mixedCaseUndoneStatus() {

        Task task = new Task(
                6,
                "Wash the Car",
                "Wash and clean the car",
                "2026-10-09",
                90,
                "UnDoNe",
                "Clean inside and outside",
                "Personal",
                "Medium"
        );

        TaskBO taskBO = new TaskBO();

        double actual = taskBO.calculate(task);

        assertEquals(90, actual);
        assertTrue(actual > 0);
    }
}