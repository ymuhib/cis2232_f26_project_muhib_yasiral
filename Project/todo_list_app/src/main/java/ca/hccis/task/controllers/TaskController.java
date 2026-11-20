
package ca.hccis.task.controllers;

import ca.hccis.task.bo.TaskBO;
import ca.hccis.task.entity.TaskDto;
import ca.hccis.task.jpa.entity.Task;
import ca.hccis.task.repositories.TaskRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Controller to administer task CRUD.
 *
 * @author Yasir Al Muhib
 * @since 20261115
 */
@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository _tr;

    @Autowired
    public TaskController(TaskRepository tr) {
        _tr = tr;
    }

    @Autowired
    private MessageSource messageSource;

    private static final Logger logger =
            LoggerFactory.getLogger(TaskController.class);

    @RequestMapping("")
    public String home(Model model) {
        Iterable<Task> tasks = _tr.findAll();
        model.addAttribute("tasks", tasks);
        return "task/list";
    }

    /**
     * Page to edit
     *
     * @param id ID
     * @param model Model
     * @return Edit page
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        Optional<Task> task = _tr.findById(id);

        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "task/add";
        }

        model.addAttribute("messageError", "Could not load the tasks");
        Iterable<Task> tasks = _tr.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("task", new Task());
        return "task/list";
    }

    /**
     * Page to add
     *
     * @param model Model
     * @return Add page
     */
    @RequestMapping("/add")
    public String add(Model model) {
        Task newTask = new Task();
        model.addAttribute("task", newTask);
        return "task/add";
    }

    /**
     * Submit method that processes add and edit.
     *
     * @param model Model
     * @param task What is being added or modified
     * @param bindingResult Result of validation
     * @return Add page with errors or task list
     */
    @RequestMapping("/submit")
    public String submit(Model model,
                         @Valid @ModelAttribute("task") Task task,
                         BindingResult bindingResult) {

        boolean valid = true;

        if (!valid || bindingResult.hasErrors()) {
            System.out.println("--------------------------------------------");
            System.out.println("Validation error - Task");

            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error.getObjectName() + "-" + error.toString() + "-" + error.getDefaultMessage());
            }

            System.out.println("--------------------------------------------");

            model.addAttribute("task", task);
            return "task/add";
        }

        task.setPriorityScore(TaskBO.calculatePriorityScore(task));

        _tr.save(task);
        return "redirect:/task";
    }

    @RequestMapping("/list/edit")
    public String showCreateForm(Model model) {

        TaskDto taskForm = new TaskDto();

        Iterable<Task> tasks = _tr.findAll();
        ArrayList<Task> tempList = new ArrayList<>();

        for (Task current : tasks) {
            tempList.add(current);
        }

        taskForm.setTasks(tempList);
        model.addAttribute("form", taskForm);
        return "task/listedit";
    }

    @RequestMapping("/list/edit/submit")
    public String listEditSubmit(@ModelAttribute TaskDto form, Model model) {

        for (Task current : form.getTasks()) {
            current.setPriorityScore(TaskBO.calculatePriorityScore(current));
        }

        _tr.saveAll(form.getTasks());

        TaskDto taskForm = new TaskDto();

        Iterable<Task> tasks = _tr.findAll();
        ArrayList<Task> tempList = new ArrayList<>();

        for (Task current : tasks) {
            tempList.add(current);
        }

        taskForm.setTasks(tempList);
        model.addAttribute("form", taskForm);
        return "task/listedit";
    }

    /**
     * Delete a task
     *
     * @param id ID
     * @param model Model
     * @return Task list
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {

        _tr.deleteById(id);

        model.addAttribute("messageSuccess", "Deleted task");

        Iterable<Task> tasks = _tr.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("task", new Task());

        return "task/list";
    }
}

