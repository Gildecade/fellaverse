package com.fellaverse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.fellaverse.backend.bean.Record;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query(value = "delete from Record r where r.id.id = ?1 and r.id.userId = ?2")
    @Transactional
    @Modifying
    void deleteByIdAndUserId(Long id, Long userId);

    List<Record> findByUser_Id(Long id);

    @Query(value = "insert into record (create_date_time, weights, quantity, num_of_sets, user_id, exercise_id) values (?1,?2,?3,?4,?5,?6);", nativeQuery = true)
    @Transactional
    @Modifying
    void addRecord(LocalDateTime create_date_time, Float weights, Integer quantity, Integer numOfSets, Long userId, Long exerciseId);


}