package com.example.demo.services;

import com.example.demo.dao.ModuleRepository;
import com.example.demo.dao.TaskRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Module;
import com.example.demo.entities.Task;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService implements IServiceModule {

    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void addModule(Module module) {
        moduleRepository.save(module);  // Add a new module
    }

    @Override
    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);  // Delete module by ID
    }

    @Override
    public List<Module> getAllModules() {
        return moduleRepository.findAll();  // Retrieve all modules
    }

    @Override
    public Module getModuleById(Long id) {
        return moduleRepository.findById(id).orElse(null);  // Retrieve module by ID
    }

    public List<Module> getModulesByUserId(Long userId) {
        // Assuming User entity has a mapped list of modules
        User user = userRepository.findById(userId).orElse(null);
        return user != null ? user.getModules() : List.of();
    }

    public List<Task> getTasksByModuleId(Long moduleId) {
        return taskRepository.findByModuleId(moduleId);
    }
}
