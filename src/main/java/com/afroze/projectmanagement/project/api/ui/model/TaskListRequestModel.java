package com.afroze.projectmanagement.project.api.ui.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.List;

@SuppressFBWarnings({"EI_EXPOSE_REP2", "EI_EXPOSE_REP"})
public class TaskListRequestModel {
    private List<TaskRequestModel> tasks;

    public List<TaskRequestModel> getTasks() {
        return tasks;
    }

    @SuppressWarnings("unused")
    public void setTasks(List<TaskRequestModel> tasks) {
        this.tasks = tasks;
    }
}
