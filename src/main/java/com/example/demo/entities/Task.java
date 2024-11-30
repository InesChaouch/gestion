package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "task_table")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String status;

    // OneToMany with User (A User can have many Tasks)
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    // ManyToOne with Project (A Task belongs to one Project)
    @ManyToOne
    @JoinColumn(name = "module_id")
    @JsonManagedReference
    private Module module;

    @Override
    public String toString() {
        return "Task [id=" + id + ", description=" + description + ", status=" + status +
                ", user=" + (user != null ? user.toString() : "No User") +
                ", project=" + (module != null ? module.toString() : "No Module") + "]";
    }
}


