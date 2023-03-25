package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.ProList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProListRepository extends JpaRepository<ProList, Long> {
}
