package com.energizeglobal.itpm.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Objects;

@XmlRootElement
@Entity
@Table(name = "comments")
@Getter
@Setter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    private UserEntity publisherUserEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_task_id")
    private TaskEntity taskEntity;

    @Column(name = "text", length = 1500)
    private String text;

    @CreationTimestamp
    @Column(name = "creation_timestamp", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "update_timestamp")
    private LocalDateTime modified;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;

        return Objects.equals(id, that.id) &&
                ((publisherUserEntity != null && that.getPublisherUserEntity() != null) &&
                        publisherUserEntity.getId().equals(that.getPublisherUserEntity().getId()))
                &&
                Objects.equals(taskEntity, that.taskEntity) &&
                Objects.equals(text, that.text) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(modified, that.modified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
