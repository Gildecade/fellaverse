package com.fellaverse.backend.bean;

import com.fellaverse.backend.enumerator.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
/**
 * Bean file for homepage announcement.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Table(name = "pro_list")
public class ProList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 60)
    private String title;

    @NotNull
    @Column(name = "description", nullable = false)
    private Instant description;

    @Column(name = "extra", nullable = true)
    private String extra;

    @Column(name = "content", nullable = false)
    private String content;
}