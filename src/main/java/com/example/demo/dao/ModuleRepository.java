package com.example.demo.dao;

import com.example.demo.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ModuleRepository extends JpaRepository<Module, Long> {

}
