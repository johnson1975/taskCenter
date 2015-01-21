package com.chaojidaogou.taskcenter.web;

import com.chaojidaogou.taskcenter.common.Response;
import com.chaojidaogou.taskcenter.domain.task.Task;
import com.chaojidaogou.taskcenter.domain.task.TaskType;
import com.chaojidaogou.taskcenter.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by Administrator on 2015/1/20.
 */
@Controller(value = "taskController")
public class TaskController {
    @Inject
    private TaskService taskService;

    @RequestMapping(value = "/taskTypes", method = RequestMethod.POST)
    @ResponseBody
    public Response addTaskType(@RequestBody TaskType taskType) {
        return Response.successResponse(taskService.addTaskType(taskType));
    }

    @RequestMapping(value = "/taskTypes", method = RequestMethod.GET)
    @ResponseBody
    public Response getTaskTypes() {
        return Response.successResponse(taskService.getTaskTypes());
    }

    @RequestMapping(value = "/taskTypes/{taskTypeId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response removeTaskType(@PathVariable Long taskTypeId) {
        return Response.successResponse(taskService.removeTaskType(taskTypeId));
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    @ResponseBody
    public Response addTaskType(@RequestBody Task task) {
        return Response.successResponse(taskService.addTask(task));
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    @ResponseBody
    public Response getTaskType() {
        return Response.successResponse(taskService.getTasks());
    }

    @RequestMapping(value = "/tasks/{taskId}/schedule", method = RequestMethod.POST)
    @ResponseBody
    public Response scheduleTask(@PathVariable Long taskId) {
        return Response.successResponse(taskService.scheduleTask(taskId));
    }
    @RequestMapping(value = "/users/{uid}/inbox", method = RequestMethod.GET)
    @ResponseBody
    public Response getTasksByUid(@PathVariable Long uid) {
        return Response.successResponse(taskService.getTasks(uid));
    }
}
