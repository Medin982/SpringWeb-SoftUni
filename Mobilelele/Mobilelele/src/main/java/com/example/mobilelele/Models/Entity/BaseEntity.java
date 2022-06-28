package com.example.mobilelele.Models.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

//    @Column(nullable = false)
    protected LocalDate created;

//    @Column(nullable = false)
    protected LocalDate modified;

    @PrePersist
    private void prePersist() {
        setCreated(LocalDate.now());
        setModified(LocalDate.now());
    }
    @PreUpdate
    private void preUpdate() {
        setModified(LocalDate.now());
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
