package com.fellaverse.backend.bean;

import com.fellaverse.backend.enumerator.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 60)
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 60)
    private String phoneNumber;

    @Column(name = "wallet", nullable = false, columnDefinition = "bigint default 1000")
    private Long wallet;

    // use columnDefinition to add specific constraints manually
    @Column(name = "status", nullable = false, columnDefinition = "varchar(60) default 'NORMAL'")
    // Enum pass by string
    @Enumerated(EnumType.STRING)
    // create a enum in package enumerator
    private UserStatus status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Record> records = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Course> courses = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<Function> functions;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Schedule> schedules = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<CheckIn> checkIns = new LinkedHashSet<>();

}