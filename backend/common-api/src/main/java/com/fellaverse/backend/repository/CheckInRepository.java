package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.CheckIn;
import com.fellaverse.backend.bean.CheckInId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.fellaverse.backend.projection.CheckInInfo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, CheckInId> {
    Set<CheckIn> findByStartDateTimeBeforeAndEndDateTimeAfter(Instant startDateTime, Instant endDateTime);
    List<CheckIn> findByUser_Id(Long id);

    @Query(value = "select c from CheckIn c where c.startDateTime < ?2 and c.endDateTime > ?1")
    Set<CheckInInfo> isOverlap(Instant start, Instant end);
}
