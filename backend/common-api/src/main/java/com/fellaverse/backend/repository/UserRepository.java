package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Set<User> findByEmailContains(@NonNull String email);
    Set<User> findByUsernameContains(@NonNull String username);
    public User findByEmail(String email);
}
