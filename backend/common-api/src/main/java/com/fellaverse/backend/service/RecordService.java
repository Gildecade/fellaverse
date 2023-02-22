package com.fellaverse.backend.service;
import com.fellaverse.backend.bean.Record;

import java.util.List;

public interface RecordService {

    void addRecord(Record record);

    void deleteRecord(Long id, Long userId);

    List<Record> findRecordByUserId(Long userId);














}
