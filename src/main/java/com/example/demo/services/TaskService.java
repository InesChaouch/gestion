package com.example.demo.services;

import com.example.demo.dao.TaskRepository;
import com.example.demo.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements IServiceTask {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void addTask(Task task) {
        taskRepository.save(task);  // Add a new task
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);  // Delete task by ID
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();  // Retrieve all tasks
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);  // Retrieve task by ID
    }

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);  // Custom method to fetch tasks by user
    }

    @Override
    public Page<Task> findAllWithPagination(Pageable pageable) {
        return taskRepository.findAll(pageable);  // Pagination for all tasks
    }

    @Override
    public List<Task> getTasksByModuleId(Long moduleId) {
        // Assuming you have a method in the repository to find tasks by moduleId
        return taskRepository.findByModuleId(moduleId);
    }


    public void updateTask(Task task) {
        // Save the updated task back to the database
        taskRepository.save(task);
    }

}
