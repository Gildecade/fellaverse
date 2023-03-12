package com.fellaverse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.fellaverse.backend.bean.Record;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query(value = "delete from Record r where r.id.id = ?1 and r.id.userId = ?2")
    @Transactional
    @Modifying
    void deleteByIdAndUserId(Long id, Long userId);

    List<Record> findByUser_Id(Long id);


}