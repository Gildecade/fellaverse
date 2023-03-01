package com.fellaverse.backend.service;

import com.fellaverse.backend.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fellaverse.backend.bean.Record;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService{

    @Autowired
    private RecordRepository recordRepository;


    @Override
    public void addRecord(Record record) {
        recordRepository.save(record);
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
