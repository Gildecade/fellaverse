package com.fellaverse.backend.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;
/**
 * bean file for application function.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "functionality")
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Function {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "function_name", unique = true, nullable = false, length = 60)
    private String functionName;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany @JoinTable(
            name = "user_function",
            joinColumns = @JoinColumn(name = "function_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;
}
