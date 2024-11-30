package com.example.demo.services;

import com.example.demo.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServiceTask {
    void addTask(Task task);
    void deleteTask(Long id);
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    List<Task> getTasksByUserId(Long userId);
    List<Task> getTasksByModuleId(Long moduleId);
    Page<Task> findAllWithPagination(Pageable pageable);
}
