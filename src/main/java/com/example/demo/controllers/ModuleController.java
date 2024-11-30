package com.example.demo.controllers;

import com.example.demo.entities.Module;
import com.example.demo.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    // Display all modules
    @GetMapping
    public String getAllModules(Model model) {
        List<Module> modules = moduleService.getAllModules();
        model.addAttribute("modules", modules);
        return "modules/list"; // Points to `src/main/resources/templates/modules/list.html`
    }

    // Display a form to add a new module
    @GetMapping("/new")
    public String showAddModuleForm(Model model) {
        model.addAttribute("module", new Module());
        return "modules/new"; // Points to `src/main/resources/templates/modules/new.html`
    }

    // Handle form submission for adding a new module
    @PostMapping
    public String addModule(@ModelAttribute Module module) {
        moduleService.addModule(module);
        return "redirect:/modules"; // Redirect to the list of modules
    }

    // Display details of a specific module by ID
    @GetMapping("/{id}")
    public String getModuleById(@PathVariable Long id, Model model) {
        Module module = moduleService.getModuleById(id);
        model.addAttribute("module", module);
        return "modules/detail"; // Points to `src/main/resources/templates/modules/detail.html`
    }

    // Delete a module by ID
    @GetMapping("/delete/{id}")
    public String deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return "redirect:/modules"; // Redirect to the list of modules
    }
}
