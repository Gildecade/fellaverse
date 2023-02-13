package com.fellaverse.backend.bean;

import jakarta.persistence.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Id
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "create_tate_time", nullable = false)
    @JdbcTypeCode(SqlTypes.DATE)
    private LocalDateTime createTateTime;

    @Column(name = "weights", nullable = false)
    @JdbcTypeCode(SqlTypes.FLOAT)
    private Float weights;

    @Column(name = "quantity", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer quantity;

    @Column(name = "num_of_sets", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer numOfSets;

    public Integer getNumOfSets() {
        return numOfSets;
    }

    public void setNumOfSets(Integer numOfSets) {
        this.numOfSets = numOfSets;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getWeights() {
        return weights;
    }

    public void setWeights(Float weights) {
        this.weights = weights;
    }

    public LocalDateTime getCreateTateTime() {
        return createTateTime;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Record record = (Record) o;
        return id != null && Objects.equals(id, record.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}