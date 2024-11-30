package com.example.demo.services;

import com.example.demo.dao.ModuleRepository;
import com.example.demo.entities.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService implements IServiceModule {

    @Autowired
    private ModuleRepository moduleRepository;

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
}
