package com.energizeglobal.itpm.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@XmlRootElement
@Entity
@Table(name = "projects")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class ProjectEntity {
    @Id
    @Column(name = "id", unique = true, updatable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_publisher")
    private UserEntity creator;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;//todo creation date time

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "projectEntity")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<SprintEntity> sprintEntities = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectEntity that = (ProjectEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                ((creator != null && that.creator != null) && creator.getId().equals(that.creator.getId()))
                &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(sprintEntities, that.sprintEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", publisher=" + creator.getId() +
                ", createdAt=" + createdAt +
                ", sprintEntities=" + sprintEntities.stream().map(SprintEntity::getId).collect(Collectors.toList()) +
                '}';
    }

}
