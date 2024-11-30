package com.example.demo.services;

import com.example.demo.entities.Module;

import java.util.List;
import java.util.Optional;

public interface IServiceModule {
    void addModule(Module module);
    void deleteModule(Long id);
    List<Module> getAllModules();
    Module getModuleById(Long id);
}
