package com.fellaverse.backend.bean;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "record")
public class Record {
    @EmbeddedId
    private RecordId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "create_date_time", nullable = false)
    private LocalDate createDateTime;

    @NotNull
    @Column(name = "weights", nullable = false)
    private Float weights;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "num_of_sets", nullable = false)
    private Integer numOfSets;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    public RecordId getId() {
        return id;
    }

    public void setId(RecordId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDate createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Float getWeights() {
        return weights;
    }

    public void setWeights(Float weights) {
        this.weights = weights;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getNumOfSets() {
        return numOfSets;
    }

    public void setNumOfSets(Integer numOfSets) {
        this.numOfSets = numOfSets;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

}