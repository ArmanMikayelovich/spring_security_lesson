package com.energizeglobal.itpm.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement
@Entity
@Table(name = "sprints")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class SprintEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_project_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProjectEntity projectEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    private UserEntity creatorUserEntity;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime creationTimestamp;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "dead_line")
    private LocalDate deadLine;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "sprintEntity", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<TaskEntity> taskEntityList = new ArrayList<>();

    @Column(name = "is_running")
    private Boolean isRunning = Boolean.FALSE;


    @Column(name = "is_finished")
    private Boolean isFinished = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintEntity that = (SprintEntity) o;
        return Objects.equals(id, that.id) &&
                (projectEntity != null && that.projectEntity != null ?
                        projectEntity.getId().equals(that.projectEntity.getId())
                        : Objects.equals(projectEntity, that.projectEntity)) &&

                (creatorUserEntity != null && that.creatorUserEntity != null) &&
                creatorUserEntity.getId().equals(that.creatorUserEntity.getId()) &&

                Objects.equals(creatorUserEntity, that.creatorUserEntity) &&
                Objects.equals(creationTimestamp, that.creationTimestamp) &&
                Objects.equals(deadLine, that.deadLine) &&
                Objects.equals(taskEntityList, that.taskEntityList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
