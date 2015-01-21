package com.chaojidaogou.taskcenter.service;

import com.chaojidaogou.taskcenter.domain.task.Task;
import com.chaojidaogou.taskcenter.domain.task.TaskType;
import com.chaojidaogou.taskcenter.domain.task.TaskView;

import java.util.List;

/**
 * Created by Administrator on 2015/1/20.
 */
public interface TaskService {
    public TaskType addTaskType(TaskType taskType);

    public List<TaskType> getTaskTypes();

    public boolean removeTaskType(Long taskTypeId);

    public Task addTask(Task task);

    public List<Task> getTasks();

    public boolean removeTask(Long taskId);

    boolean scheduleTask(Long taskId);

    public List<TaskView> getTasks(Long uid);
}
