package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Exercise;
import com.fellaverse.backend.bean.Record;
import com.fellaverse.backend.dto.RecordAddDTO;
import com.fellaverse.backend.repository.ExerciseRepository;
import com.fellaverse.backend.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService{

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public List<Exercise> findAllExercise() {
        return exerciseRepository.findAll();
    }

    @Override
    public void addRecord(RecordAddDTO recordAddDTO) {
        recordRepository.addRecord(recordAddDTO.getCreateDateTime(),
                recordAddDTO.getWeights(), recordAddDTO.getQuantity(),
                recordAddDTO.getNumOfSets(), recordAddDTO.getUserId(), recordAddDTO.getExerciseId());
    }

    @Override
    @Transactional
    public void deleteRecord(Long id, Long userId) {
        recordRepository.deleteByIdAndUserId(id, userId);
    }

    @Override
    public List<Record> findRecordByUserId(Long userId) {
        return recordRepository.findByUser_Id(userId);
    }
}
