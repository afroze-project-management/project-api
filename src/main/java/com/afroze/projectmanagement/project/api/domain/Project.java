package com.afroze.projectmanagement.project.api.domain;

import com.afroze.projectmanagement.project.api.data.Auditable;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.Set;

@SuppressFBWarnings({"EI_EXPOSE_REP2", "EI_EXPOSE_REP"})
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

    public long getCompanyId() {
        return companyId;
    }

    @SuppressWarnings("unused")
    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    @SuppressWarnings("unused")
    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}