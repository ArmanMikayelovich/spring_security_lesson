package com.energizeglobal.itpm.model;

import com.energizeglobal.itpm.model.enums.TaskPriority;
import com.energizeglobal.itpm.model.enums.TaskState;
import com.energizeglobal.itpm.model.enums.TaskTrigger;
import com.energizeglobal.itpm.model.enums.TaskType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement
@Entity
@Table(name = "tasks")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @Enumerated(EnumType.STRING)
    private TaskState taskState = TaskState.TODO;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority = TaskPriority.DEFAULT;

    @Column(name = "description", length = 1500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_creator_user_id")
    private UserEntity creatorUserEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_assigned_user_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private UserEntity assignedUserEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_sprint_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private SprintEntity sprintEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_project_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private ProjectEntity projectEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_project_version_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private ProjectVersionEntity projectVersionEntity;


    @ManyToMany
    @JoinTable(
            name = "task_project_versions",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "version_id"))
    List<ProjectVersionEntity> affectedProjectVersions = new ArrayList<>();


    //    @Column(name = "parent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private TaskEntity parent;

    @Enumerated(value = EnumType.STRING)
    private TaskTrigger triggerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "triggered_by", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private TaskEntity triggeredBy;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                taskType == that.taskType &&
                Objects.equals(description, that.description) &&

                (creatorUserEntity != null && that.creatorUserEntity != null) &&
                creatorUserEntity.getId().equals(that.creatorUserEntity.getId()) &&

                Objects.equals(assignedUserEntity, that.assignedUserEntity) &&
                Objects.equals(sprintEntity, that.sprintEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
