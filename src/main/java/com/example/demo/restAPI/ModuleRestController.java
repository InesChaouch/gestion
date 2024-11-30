package com.example.demo.restAPI;

import com.example.demo.entities.Module;
import com.example.demo.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
public class ModuleRestController {

    @Autowired
    private ModuleService moduleService;

    // Add a new module
    @PostMapping
    public void addModule(@RequestBody Module module) {
        moduleService.addModule(module);
    }

    // Get all modules
    @GetMapping
    public List<Module> getAllModules() {
        return moduleService.getAllModules();
    }

    // Get module by ID
    @GetMapping("/{id}")
    public Module getModuleById(@PathVariable Long id) {
        return moduleService.getModuleById(id);
    }

    // Delete module by ID
    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
    }
}
