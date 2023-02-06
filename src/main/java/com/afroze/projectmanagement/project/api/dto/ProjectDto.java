package com.afroze.projectmanagement.project.api.dto;

import java.util.ArrayList;
import java.util.List;

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
        List<TaskDto> copy = new ArrayList<>();
        for(TaskDto task: tasks) {
            copy.add(task);
        }
        this.tasks = copy;
    }

    public long getCompanyId() {
        return companyId;
    }

}
