package com.afroze.projectmanagement.project.api.dto;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.List;

@SuppressFBWarnings({"EI_EXPOSE_REP2", "EI_EXPOSE_REP"})
public class ProjectDto {
    private long id;

    private String name;

    private String tags;

    private List<TaskDto> tasks;

    private long companyId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }

    public long getCompanyId() {
        return companyId;
    }

    @SuppressWarnings("unused")
    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    @SuppressWarnings("unused")
    public void setTags(String tags) {
        this.tags = tags;
    }

    @SuppressWarnings("unused")
    public List<TaskDto> getTasks() {
        return tasks;
    }
}
