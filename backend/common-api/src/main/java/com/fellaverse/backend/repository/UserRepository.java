package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
