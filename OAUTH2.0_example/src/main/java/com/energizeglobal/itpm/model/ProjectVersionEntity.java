package com.energizeglobal.itpm.model;

import com.energizeglobal.itpm.model.enums.ProjectVersionStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "project_version")
@Getter
@Setter
public class ProjectVersionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_project_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private ProjectEntity projectEntity;

    @Column(name = "version", nullable = false, length = 50)
    private String version;


    @Enumerated(value = EnumType.STRING)
    @Column(name = "version_status")
    private ProjectVersionStatus versionStatus;

    @CreationTimestamp
    @Column(name = "registration_date", updatable = false)
    private LocalDate registrationDate = LocalDate.now();
}
