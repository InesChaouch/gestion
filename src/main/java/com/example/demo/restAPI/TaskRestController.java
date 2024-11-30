package com.example.demo.restAPI;

import com.example.demo.entities.Task;
import com.example.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    // Add a new task
    @PostMapping
    public void addTask(@RequestBody Task task) {
        taskService.addTask(task);
    }

    // Get all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Get task by ID
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // Delete task by ID
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    // Find tasks by user ID
    @GetMapping("/user/{userId}")
    public List<Task> getTasksByUserId(@PathVariable Long userId) {
        return taskService.getTasksByUserId(userId);
    }

    // Find tasks by module ID
    @GetMapping("/module/{moduleId}")
    public List<Task> getTasksByModuleId(@PathVariable Long moduleId) {
        return taskService.getTasksByModuleId(moduleId);
    }
}
