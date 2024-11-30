package com.example.demo.controllers;

import com.example.demo.entities.Task;
import com.example.demo.services.ModuleService;
import com.example.demo.services.TaskService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;


    // Display all tasks
    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks/list"; // Points to `src/main/resources/templates/tasks/list.html`
    }

    // Display a form to add a new task
    @GetMapping("/tasks/new")
    public String showAddTaskForm(Model model) {
        // Create an empty Task object to bind the form fields
        model.addAttribute("task", new Task());

        // Add all users and modules to the model
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("modules", moduleService.getAllModules());

        return "tasks/new"; // Points to 'tasks/new.html'
    }

    @PostMapping("/tasks")
    public String addTask(@ModelAttribute Task task) {
        taskService.addTask(task); // Save the task
        return "redirect:/tasks"; // Redirect to the task list after saving
    }


    // Display details of a specific task by ID
    @GetMapping("/{id}")
    public String getTaskById(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "tasks/detail"; // Points to `src/main/resources/templates/tasks/detail.html`
    }

    // Delete a task by ID
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks"; // Redirect to the list of tasks
    }

    // Display tasks associated with a specific user ID
    @GetMapping("/user/{userId}")
    public String getTasksByUserId(@PathVariable Long userId, Model model) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        model.addAttribute("tasks", tasks);
        return "tasks/user-tasks"; // Points to `src/main/resources/templates/tasks/user-tasks.html`
    }

    // Display tasks associated with a specific module ID
    @GetMapping("/module/{moduleId}")
    public String getTasksByModuleId(@PathVariable Long moduleId, Model model) {
        List<Task> tasks = taskService.getTasksByModuleId(moduleId);
        model.addAttribute("tasks", tasks);
        return "tasks/module-tasks"; // Points to `src/main/resources/templates/tasks/module-tasks.html`
    }
}
