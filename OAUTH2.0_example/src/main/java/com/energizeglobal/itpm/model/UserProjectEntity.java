package com.energizeglobal.itpm.model;

import com.energizeglobal.itpm.model.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
@Entity
@Table(name = "users_projects")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class UserProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_project_id")
    private ProjectEntity projectEntity;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProjectEntity that = (UserProjectEntity) o;
        return Objects.equals(id, that.id) &&

                (userEntity != null && that.userEntity != null) &&
                userEntity.getId().equals(that.userEntity.getId()) &&

                (projectEntity != null && that.projectEntity != null ?
                        projectEntity.getId().equals(that.projectEntity.getId())
                        : Objects.equals(projectEntity, that.projectEntity)) &&

                role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
