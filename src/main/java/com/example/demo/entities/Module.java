package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "module_table")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "module")
    @JsonBackReference
    private List<Task> tasks;

    @ManyToMany(mappedBy = "modules")
    @JsonBackReference
    private List<User> users;

    @Override
    public String toString() {
        return "Module [id=" + id + ", name=" + name + ", description=" + description + "]";
    }
}
