package com.fellaverse.backend.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "program")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "program_name", nullable = false, length = 60)
    private String programName;

    @ManyToMany(mappedBy = "programs", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private Set<Exercise> exercises = new LinkedHashSet<>();

    @OneToOne(optional = false, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_id", nullable = false, insertable = false, updatable = false)
    private Schedule schedule;

    @Column(name = "schedule_id")
    private Long scheduleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Program program = (Program) o;
        return id != null && Objects.equals(id, program.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}