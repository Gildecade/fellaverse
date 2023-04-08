package com.fellaverse.backend.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
/**
 * Bean file for exercise types
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "exercise_name", nullable = false, length = 60)
    private String exerciseName;

    @ManyToMany
    @JoinTable(name = "program_exercise",
            joinColumns = @JoinColumn(name = "exercise_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "program_id", referencedColumnName = "id"))
    private Set<Program> programs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Record> records = new LinkedHashSet<>();

}