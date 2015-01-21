package com.chaojidaogou.taskcenter.service;

import com.chaojidaogou.taskcenter.common.Keys;
import com.chaojidaogou.taskcenter.domain.task.Task;
import com.chaojidaogou.taskcenter.domain.task.TaskManager;
import com.chaojidaogou.taskcenter.domain.task.TaskType;
import com.chaojidaogou.taskcenter.domain.task.TaskView;
import com.chaojidaogou.taskcenter.repository.RedisRepository;
import com.chaojidaogou.taskcenter.repository.TaskRepository;
import com.chaojidaogou.taskcenter.repository.TaskTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/1/20.
 */

@Service(value = "taskService")
@Transactional
public class TaskServiceImpl implements TaskService {
    @Inject
    private TaskTypeRepository taskTypeRepository;
    @Inject
    private TaskRepository taskRepository;
    @Inject
    private TaskManager taskManager;
    @Inject
    private RedisRepository redisRepository;

    @PostConstruct
    private void init() {
        redisRepository.removeKeys();
    }

    @Override
    public TaskType addTaskType(TaskType taskType) {
        return taskTypeRepository.save(taskType);
    }

    @Override
    public List<TaskType> getTaskTypes() {
        return taskTypeRepository.findAll();
    }

    @Override
    public boolean removeTaskType(Long taskTypeId) {
        taskTypeRepository.delete(taskTypeId);
        return true;
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public boolean removeTask(Long taskId) {
        taskRepository.delete(taskId);
        return false;
    }

    @Override
    public boolean scheduleTask(Long taskId) {
        Task task = taskRepository.findOne(taskId);
        taskManager.schedule(task);
        return true;
    }

    @Override
    public List<TaskView> getTasks(Long uid) {
        List<String> taskIdList = redisRepository.members(Keys.uidInboxQueue(uid));
        List<TaskView> result = new ArrayList<>();
        for (String taskId : taskIdList) {
            Task task = taskRepository.findOne(Long.parseLong(taskId));
            result.add(TaskView.valueOf(task, new Date()));
        }
        return result;
    }
}
