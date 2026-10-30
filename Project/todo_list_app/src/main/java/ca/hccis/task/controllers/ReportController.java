package ca.hccis.task.controllers;

import ca.hccis.task.bo.ReportBO;
import ca.hccis.task.entity.ReportInput;
import ca.hccis.task.util.CisUtilityFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * Controller to administer reports for the project.
 *
 * @author Yasir Al Muhib
 * @since 20261028
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    /**
     * Display the reports page.
     *
     * @param model A holder for model attributes.
     * @return The reports list view.
     * @author Yasir Al Muhib
     * @since 20261028
     */
    @RequestMapping("")
    public String home(Model model) {
        logger.info("Running the ReportController home method.");
        return "report/list";
    }

    /**
     * Display the task category report input form.
     *
     * @param model A holder for model attributes.
     * @return The report input view.
     * @author Yasir Al Muhib
     * @since 20261030
     */
    @RequestMapping("/task/category")
    public String reportTaskCategory(Model model) {
        logger.info("Running the task category report method.");

        model.addAttribute("reportInput", new ReportInput());

        return "report/reportTaskCategory";
    }

    /**
     * Process and display the task category report.
     *
     * @param model A holder for model attributes.
     * @param reportInput The report input information.
     * @return The report results view.
     * @throws IOException If there is an issue writing the report file.
     * @author Yasir Al Muhib
     * @since 20261030
     */
    @RequestMapping("/task/category/submit")
    public String reportTaskCategorySubmit(Model model,
                                           @ModelAttribute("reportInput") ReportInput reportInput) throws IOException {

        logger.info("Running the task category report submit method.");

        ReportBO.processTaskCategoryReport(reportInput);

        if (reportInput.getTaskCategorySummaries() != null
                && reportInput.getTaskCategorySummaries().isEmpty()) {

            model.addAttribute("message", "No tasks found for that status");

        } else if (reportInput.getTaskCategorySummaries() != null) {

            CisUtilityFile.writeReportToFile(
                    "TasksByCategoryReport",
                    reportInput.getTaskCategorySummaries()
            );
        }

        model.addAttribute("reportInput", reportInput);
        model.addAttribute("taskCategorySummaries",
                reportInput.getTaskCategorySummaries());

        return "report/reportTaskCategory";
    }
}

