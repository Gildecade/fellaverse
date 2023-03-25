package com.fellaverse.backend.service;
import com.fellaverse.backend.bean.Exercise;
import com.fellaverse.backend.bean.Record;
import com.fellaverse.backend.dto.RecordAddDTO;

import java.util.List;

public interface RecordService {

    List<Exercise> findAllExercise();
    void addRecord(RecordAddDTO recordAddDTO);

    void deleteRecord(Long id, Long userId);

    List<Record> findRecordByUserId(Long userId);














}
