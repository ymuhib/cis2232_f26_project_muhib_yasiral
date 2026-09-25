package ca.hccis.files;

import ca.hccis.files.entity.Task;
import ca.hccis.util.CisUtility;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

/**
 * Controls the overall flow of the program.
 *
 * @author Yasir Al Muhib
 * @since 20260925
 */
public class Controller {

    public static final String EXIT = "X";

    public static final String MENU = "A) Add" + System.lineSeparator()
            + "V) View" + System.lineSeparator()
            + EXIT + ") eXit" + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_EXIT = "Goodbye";

    private static HashMap<Integer, Task> taskMap = new HashMap();
    private static Gson gson = new Gson();

    // File path where task information will be stored.
    public static final String PATH_NAME =
            "c:\\cis2232\\data_muhib_yasiral.json";

    /**
     * Main method that controls the program menu.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        initialize();

        String menuOption;

        do {

            menuOption = CisUtility.getInputString(MENU).toUpperCase();

            switch (menuOption) {

                case EXIT:
                    System.out.println(MESSAGE_EXIT);
                    break;

                case "A":
                    add();
                    break;

                case "V":
                    viewAll();
                    break;

                default:
                    System.out.println(MESSAGE_ERROR);
                    break;
            }

        } while (!menuOption.equals(EXIT));
    }

    /**
     * Adds a new task to the HashMap and saves it to the JSON file.
     */
    public static void add() {

        Task newTask = new Task();

        System.out.println("--Add Task--");

        newTask.getInformation();

        // Auto-assign next integer ID if ID is 0 or unassigned.
        if (newTask.getId() == 0) {

            int maxId = taskMap.keySet()
                    .stream()
                    .mapToInt(v -> v)
                    .max()
                    .orElse(0);

            newTask.setId(maxId + 1);
        }

        taskMap.put(newTask.getId(), newTask);

        writeAll();
    }

    /**
     * Displays all tasks currently stored in the HashMap.
     */
    public static void viewAll() {

        readAll();

        System.out.println("--View Tasks--");

        for (Task current : taskMap.values()) {

            System.out.println(current);
        }
    }

    /**
     * Writes all tasks from the HashMap to the JSON file.
     */
    public static void writeAll() {

        try {

            FileWriter writer = new FileWriter(PATH_NAME, false);

            for (Task current : taskMap.values()) {

                writer.append(gson.toJson(current));
                writer.append(System.lineSeparator());

                System.out.println(
                        "Successfully written JSON string to file."
                );
            }

            writer.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * Reads all tasks from the JSON file into the HashMap.
     */
    public static void readAll() {

        try {

            List<String> lines =
                    Files.readAllLines(Paths.get(PATH_NAME));

            for (int i = 0; i < lines.size(); i++) {

                Task taskFromJson =
                        gson.fromJson(lines.get(i), Task.class);

                taskMap.put(
                        taskFromJson.getId(),
                        taskFromJson
                );
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * Initializes the program.
     *
     * Creates the required directory if it does not exist.
     * If the JSON file exists, the existing tasks are loaded.
     * Otherwise, sample tasks are created and saved.
     */
    public static void initialize() {

        Path path = Paths.get(PATH_NAME);

        try {

            // Create the cis2232 directory if it does not exist.
            if (!Files.exists(path.getParent())) {

                Files.createDirectories(path.getParent());
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        // Check if the file exists.
        if (Files.exists(path)) {

            System.out.println("Tasks exist.");

            readAll();

        } else {

            Task task = new Task(
                    1,
                    "Complete Java Assignment",
                    "Complete the File I/O assignment",
                    "2026-09-30",
                    120,
                    "Not Started",
                    "Review the class sample before submitting",
                    "School",
                    "High"
            );

            Task task2 = new Task(
                    2,
                    "Study for Exam",
                    "Review the material for the upcoming exam",
                    "2026-10-05",
                    90,
                    "Not Started",
                    "Review chapters 1 to 5",
                    "School",
                    "Medium"
            );

            Task task3 = new Task(
                    3,
                    "Submit Project",
                    "Submit the completed project to GitHub",
                    "2026-10-10",
                    60,
                    "In Progress",
                    "Check all files before submission",
                    "School",
                    "High"
            );

            Task task4 = new Task(
                    4,
                    "Buy Groceries",
                    "Purchase groceries for the week",
                    "2026-09-27",
                    45,
                    "Not Started",
                    "Make a shopping list",
                    "Personal",
                    "Low"
            );

            Task task5 = new Task(
                    5,
                    "Work on Resume",
                    "Update resume with recent experience",
                    "2026-10-01",
                    60,
                    "Not Started",
                    "Add current education information",
                    "Personal",
                    "Medium"
            );

            taskMap.put(task.getId(), task);
            taskMap.put(task2.getId(), task2);
            taskMap.put(task3.getId(), task3);
            taskMap.put(task4.getId(), task4);
            taskMap.put(task5.getId(), task5);

            writeAll();
        }
    }
}