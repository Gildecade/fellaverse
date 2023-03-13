package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByUsernameOrEmail(String username, String email);
    boolean existsByUsernameOrEmail(String username, String email);
    User findByEmail(String email);
    Set<User> findByEmailContains(@NonNull String email);
    Set<User> findByUsernameContains(@NonNull String username);
}
