package com.afroze.projectmanagement.project.api.ui.model;

import java.util.ArrayList;
import java.util.List;

public class TaskListRequestModel {
    private List<TaskRequestModel> tasks;

    public List<TaskRequestModel> getTasks() {
        List<TaskRequestModel> copy = new ArrayList<>();
        for(TaskRequestModel task : this.tasks) {
            copy.add(task);
        }
        return copy;
    }

    public void setTasks(List<TaskRequestModel> tasks) {
        List<TaskRequestModel> copy = new ArrayList<>();
        for (var task : tasks) {
            copy.add(task);
        }
        this.tasks = copy;
    }
}
