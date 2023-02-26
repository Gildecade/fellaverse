package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Exercise;
import com.fellaverse.backend.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExerciseManageManageServiceImpl implements ExerciseManageService
{
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Override
    public Boolean addExercise(Exercise exercise) {
        if (exerciseRepository.findByExerciseName(exercise.getExerciseName()) != null)
            return false;
        else {
            exerciseRepository.save(exercise);
            return true;
        }
    }

    @Override
    public Boolean editExercise(Exercise exercise) {
        if (exerciseRepository.findById(exercise.getId()) == null)
            return false;
        else {
            exerciseRepository.save(exercise);
            return true;
        }
    }

    @Override
    public Boolean deleteExercise(Long id) {
        if (exerciseRepository.existsById(id)) {
            exerciseRepository.deleteById(id);
            return true;
        }
        else
            return false;
    }

    @Override
    public Set<Exercise> findAllExercise() {
        List<Exercise> sourceList = exerciseRepository.findAll();
        return new HashSet<>(sourceList);
    }

    @Override
    public Set<Exercise> findExerciseByKeyword(String keyword) {
        return exerciseRepository.findByExerciseNameContains(keyword);
    }

    @Override
    public Exercise findExerciseById(Long id) {
        return exerciseRepository.findById(id).orElse(null);
    }
}
