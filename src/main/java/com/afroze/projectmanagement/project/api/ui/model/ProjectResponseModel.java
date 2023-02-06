package com.afroze.projectmanagement.project.api.ui.model;

import java.util.List;

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
}