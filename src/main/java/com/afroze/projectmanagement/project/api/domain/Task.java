package com.afroze.projectmanagement.project.api.domain;

import com.afroze.projectmanagement.project.api.data.Auditable;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.persistence.*;

import java.math.BigDecimal;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@Entity
@Table(name = "task", indexes = {
        @Index(name = "idx_task_id", columnList = "id")
})
public class Task extends Auditable<String, Long> {
    private String name;
    private String description;
    private BigDecimal estimatedEffort;
    private BigDecimal actualEffort;
    private boolean isComplete;
    private long userId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("unused")
    public String getDescription() {
        return description;
    }

    @SuppressWarnings("unused")
    public void setDescription(String description) {
        this.description = description;
    }

    @SuppressWarnings("unused")
    public BigDecimal getEstimatedEffort() {
        return estimatedEffort;
    }

    @SuppressWarnings("unused")
    public void setEstimatedEffort(BigDecimal estimatedEffort) {
        this.estimatedEffort = estimatedEffort;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @SuppressWarnings("unused")
    public BigDecimal getActualEffort() {
        return actualEffort;
    }

    @SuppressWarnings("unused")
    public void setActualEffort(BigDecimal actualEffort) {
        this.actualEffort = actualEffort;
    }

    @SuppressWarnings("unused")
    public boolean isComplete() {
        return isComplete;
    }

    @SuppressWarnings("unused")
    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    @SuppressWarnings("unused")
    public long getUserId() {
        return userId;
    }

    @SuppressWarnings("unused")
    public void setUserId(long userId) {
        this.userId = userId;
    }
}
