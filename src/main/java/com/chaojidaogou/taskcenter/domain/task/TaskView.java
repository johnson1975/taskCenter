package com.chaojidaogou.taskcenter.domain.task;

import java.util.Date;

/**
 * Created by Johnson on 2015/1/21.
 */
public class TaskView {
    private Long taskId;
    private Integer taskType;
    private String title;
    private String content;
    private Date publishDate;
    private Date createDate;

    public TaskView(Long taskId, Integer taskType, String title, String content, Date createDate) {
        this.taskId = taskId;
        this.taskType = taskType;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public static TaskView valueOf(Task task, Date publishDate) {
        TaskView result = new TaskView(task.getTaskId(), task.getTaskType(), task.getTitle(), task.getAction(), task.getCreateDate());
        result.setPublishDate(publishDate);
        return result;
    }

}
