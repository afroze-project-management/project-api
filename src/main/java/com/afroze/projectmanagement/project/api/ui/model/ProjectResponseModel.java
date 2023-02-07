package com.afroze.projectmanagement.project.api.ui.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.List;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public class ProjectResponseModel {
    private Long id;

    private String name;

    private String tags;

    private List<TaskResponseModel> tasks;

    private long companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("unused")
    public String getTags() {
        return tags;
    }

    @SuppressWarnings("unused")
    public void setTags(String tags) {
        this.tags = tags;
    }

    @SuppressWarnings("unused")
    public List<TaskResponseModel> getTasks() {
        return tasks;
    }

    @SuppressWarnings("unused")
    public void setTasks(List<TaskResponseModel> tasks) {
        this.tasks = tasks;
    }

    @SuppressWarnings("unused")
    public long getCompanyId() {
        return companyId;
    }

    @SuppressWarnings("unused")
    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }
}