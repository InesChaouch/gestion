package com.example.demo.controllers;

import com.example.demo.entities.Task;
import com.example.demo.entities.TaskStatus; // Add import for TaskStatus enum
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

    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("statuses", TaskStatus.values());  // Add available statuses
        return "tasks/list";
    }

    @GetMapping("/new")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("modules", moduleService.getAllModules());
        return "tasks/new";
    }

    @PostMapping
    public String addTask(@ModelAttribute Task task) {
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/updateStatus")
    public String updateTaskStatus(@PathVariable Long id, @RequestParam TaskStatus status, Model model) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            task.setStatus(status);
            taskService.updateTask(task);
        }
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String getTaskDetails(@PathVariable("id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "tasks/detail";
    }

}
