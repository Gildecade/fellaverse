package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    @Query(value = "select u.wallet from User u where u.id = ?1")
    Float lockUserWallet(Long userId);
    @Modifying
    @Query("update User u SET u.wallet = ?2 where u.id = ?1")
    Integer updateUserWallet(Long userId, Float price);
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select u.wallet from User u where u.id = ?1")
    Float findUserWallet(Long userId);
}
