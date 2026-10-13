package ca.hccis.multithreading;

import ca.hccis.multithreading.bo.TaskBO;
import ca.hccis.multithreading.entity.Task;
import ca.hccis.multithreading.threads.Runner1;
import ca.hccis.multithreading.threads.Runner2;
import ca.hccis.util.CisUtility;
import com.google.gson.Gson;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CancellationException;

/**
 * Controls the overall flow of the program.
 *
 * @author yam
 * @since 20260925
 */
public class Controller {

    public static final String EXIT = "X";
    public static final String MENU = "A) Add" + System.lineSeparator()
            + "V) View" + System.lineSeparator()
            + EXIT + ") eXit" + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_EXIT = "Goodbye";

    private static final HashMap<Integer, Task> taskMap = new HashMap<>();
    private static final Gson gson = new Gson();
    private static final TaskBO taskBO = new TaskBO();

    public static final String PATH_NAME =
            "c:\\cis2232\\data_muhib_yasiral.json";

    /**
     * Initializes the application and starts both threads.
     *
     */
    public static void main(String[] args) {
        initialize();

        // Start the console and GUI threads
        Runner1 runner1 = new Runner1();
        Thread runner2 = new Thread(new Runner2());

        runner1.start();
        runner2.start();
    }

    /**
     * Displays the console menu and processes user selections.
     */
    public static void menuConsole() {
        String menuOption;

        do {
            menuOption = CisUtility.getInputString(MENU).toUpperCase();

            switch (menuOption) {
                case "A":
                    add();
                    break;
                case "V":
                    viewAll();
                    break;
                case EXIT:
                    System.out.println(MESSAGE_EXIT);
                    break;
                default:
                    System.out.println(MESSAGE_ERROR);
                    break;
            }
        } while (!menuOption.equals(EXIT));
    }

    /**
     * Displays the GUI menu and processes user selections.
     */
    public static void menuGUI() {
        String menuOption;

        do {
            menuOption = JOptionPane.showInputDialog(
                    null, "A) Add\nV) View\nX) Exit");

            // End the GUI thread if the user closes the dialog
            if (menuOption == null) {
                break;
            }

            menuOption = menuOption.toUpperCase().trim();

            switch (menuOption) {
                case "A":
                    addGUI();
                    break;
                case "V":
                    viewAllGUI();
                    break;
                case EXIT:
                    JOptionPane.showMessageDialog(null, MESSAGE_EXIT);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, MESSAGE_ERROR);
                    break;
            }
        } while (!menuOption.equals(EXIT));
    }

    /**
     * Collects and saves a task using console input.
     */
    public static void add() {
        Task newTask = new Task();

        System.out.println("--Add Task--");
        newTask.getInformation();

        // Save the task and display the updated calculation
        saveTask(newTask);
        displayAccumulatorConsole();
    }

    /**
     * Collects and saves a task using GUI input.
     */
    public static void addGUI() {
        Task newTask = new Task();

        try {
            newTask.getInformationGUI();

            // Save the task and display the updated calculation
            saveTask(newTask);
            displayAccumulatorGUI();
        } catch (CancellationException e) {
            JOptionPane.showMessageDialog(null,
                    "Task was not added.");
        }
    }

    /**
     * Assigns an ID and saves a task to the shared map and file.
     *
     * @param newTask the task to save
     */
    public static void saveTask(Task newTask) {
        synchronized (taskMap) {
            // Load the latest saved tasks before assigning an ID
            readAll();

            // Assign the next available ID
            int maxId = taskMap.keySet().stream()
                    .mapToInt(Integer::intValue)
                    .max()
                    .orElse(0);

            newTask.setId(maxId + 1);

            // Add the task to the shared map
            taskMap.put(newTask.getId(), newTask);

            // Save all tasks to the file
            writeAll();
        }
    }

    /**
     * Displays all saved tasks in the console.
     */
    public static void viewAll() {
        synchronized (taskMap) {
            readAll();

            System.out.println("--View Tasks--");

            for (Task current : taskMap.values()) {
                System.out.println(current);
            }
        }

        displayAccumulatorConsole();
    }

    /**
     * Displays all saved tasks in a GUI dialog.
     */
    public static void viewAllGUI() {
        synchronized (taskMap) {
            readAll();

            StringBuilder output = new StringBuilder("--View Tasks--\n\n");

            for (Task current : taskMap.values()) {
                output.append(current).append("\n\n");
            }

            JOptionPane.showMessageDialog(null, output.toString());
        }

        displayAccumulatorGUI();
    }

    /**
     * Displays the task count and remaining time in the console.
     */
    public static void displayAccumulatorConsole() {
        synchronized (taskMap) {
            readAll();

            System.out.println("--Accumulator--");
            System.out.println("Total Tasks: " + taskMap.size());
            System.out.println("Remaining Estimated Time: "
                    + calculateRemainingTime() + " minutes");
        }
    }

    /**
     * Displays the task count and remaining time in a GUI dialog.
     */
    public static void displayAccumulatorGUI() {
        synchronized (taskMap) {
            readAll();

            JOptionPane.showMessageDialog(null,
                    "--Accumulator--\n"
                            + "Total Tasks: " + taskMap.size() + "\n"
                            + "Remaining Estimated Time: "
                            + calculateRemainingTime() + " minutes");
        }
    }

    /**
     * Calculates the total estimated time for unfinished tasks.
     *
     * @return the remaining estimated time in minutes
     */
    public static double calculateRemainingTime() {
        synchronized (taskMap) {
            double total = 0;

            // Add the estimated time for each unfinished task
            for (Task current : taskMap.values()) {
                total += taskBO.calculate(current);
            }

            return total;
        }
    }

    /**
     * Writes all tasks to the JSON file.
     */
    public static void writeAll() {
        synchronized (taskMap) {
            // Write each task as a separate JSON line
            try (FileWriter writer = new FileWriter(PATH_NAME, false)) {
                for (Task current : taskMap.values()) {
                    writer.append(gson.toJson(current));
                    writer.append(System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads saved tasks from the JSON file into the task map.
     */
    public static void readAll() {
        synchronized (taskMap) {
            Path path = Paths.get(PATH_NAME);

            if (!Files.exists(path)) {
                return;
            }

            try {
                // Read saved tasks from the JSON file
                List<String> lines = Files.readAllLines(path);
                taskMap.clear();

                for (String line : lines) {
                    if (!line.trim().isEmpty()) {
                        Task taskFromJson = gson.fromJson(line, Task.class);
                        taskMap.put(taskFromJson.getId(), taskFromJson);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates the data directory and loads or seeds task data.
     */
    public static void initialize() {
        Path path = Paths.get(PATH_NAME);

        try {
            // Create the data folder if it does not exist
            if (path.getParent() != null
                    && !Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        synchronized (taskMap) {
            if (Files.exists(path)) {
                System.out.println("Tasks exist.");
                readAll();
            } else {
                // Create sample tasks for the first run
                taskMap.put(1, new Task(1, "Complete Java Assignment",
                        "Finish the Java assignment", "2026-10-05",
                        120, "undone", "", "School", "High"));

                taskMap.put(2, new Task(2, "Study for Exam",
                        "Review course materials", "2026-10-08",
                        90, "undone", "", "School", "High"));

                taskMap.put(3, new Task(3, "Submit Project",
                        "Complete and submit the project", "2026-10-10",
                        60, "undone", "", "School", "Medium"));

                taskMap.put(4, new Task(4, "Buy Groceries",
                        "Buy groceries for the week", "2026-10-06",
                        45, "undone", "", "Personal", "Low"));

                taskMap.put(5, new Task(5, "Work on Resume",
                        "Update resume for job applications", "2026-10-12",
                        60, "undone", "", "Work", "Medium"));

                // Save the sample tasks
                writeAll();
            }
        }
    }
}