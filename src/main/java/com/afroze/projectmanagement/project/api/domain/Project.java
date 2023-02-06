package com.afroze.projectmanagement.project.api.domain;

import com.afroze.projectmanagement.project.api.data.Auditable;
import jakarta.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project", indexes = {
        @Index(name = "idx_project_id", columnList = "id")
})

public class Project extends Auditable<String, Long> {

    private String name;

    private String tags;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Task> tasks;

    private long companyId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Set<Task> getTasks() {
        return Collections.unmodifiableSet(tasks);
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = new HashSet<Task>(tasks);
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }
}