package com.example.demo.controllers;

import com.example.demo.entities.Module;
import com.example.demo.entities.Task;
import com.example.demo.services.ModuleService;
import com.example.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private TaskService taskService;

    // Display all modules
    @GetMapping("/list")
    public String getAllModules(Model model) {
        List<Module> modules = moduleService.getAllModules();
        model.addAttribute("modules", modules);
        return "modules/list";
    }

    @GetMapping("/new")
    public String showAddModuleForm(Model model) {
        model.addAttribute("module", new Module());
        return "modules/new";
    }

    @PostMapping("/new")
    public String addModule(@ModelAttribute("module") Module module, RedirectAttributes redirectAttributes) {
        try {
            moduleService.addModule(module);
            redirectAttributes.addFlashAttribute("successMessage", "Module added successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add module.");
        }
        return "redirect:/modules/list";
    }

    // Delete a module by ID
    @GetMapping("/delete/{id}")
    public String deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return "redirect:/modules/list";
    }

    @GetMapping("/Details/{id}")
    public String showModuleDetails(@PathVariable Long id, Model model) {
        Module module = moduleService.getModuleById(id);
        model.addAttribute("module", module);
        return "modules/module";
    }

    @GetMapping("/{id}")
    public String getModuleById(@PathVariable Long id, Model model) {
        Module module = moduleService.getModuleById(id);
        if (module == null) {
            return "redirect:/modules";
        }

        List<Task> tasks = taskService.getTasksByModuleId(id);

        model.addAttribute("module", module);
        model.addAttribute("tasks", tasks);

        return "modules/detail";
    }

}
