package com.fellaverse.backend.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
/**
 * Bean file for role of the application administrator.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "role")
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", unique = true, nullable = false, length = 60)
    private String roleName;

    @Column(name = "description", unique = true, nullable = false)
    private String description;
}
