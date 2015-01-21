package com.chaojidaogou.taskcenter.domain.task;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2015/1/16.
 */
@Entity
public class Task implements Serializable {
    private static final long serialVersionUID = -8149150187117050112L;
    private static final String INBOX_VALUE_PATTERN = "taskId:%d:type:%d:action:%s";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String title;
    private Integer taskType;
    private Long userGroup;
    private String action;
    private String scheduleExpression;
    private Date createDate = new Date();

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Long getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Long userGroup) {
        this.userGroup = userGroup;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getScheduleExpression() {
        return scheduleExpression;
    }

    public void setScheduleExpression(String scheduleExpression) {
        this.scheduleExpression = scheduleExpression;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Transient
    public String getInboxValue() {
        return String.format(INBOX_VALUE_PATTERN, taskId, taskType, action);
    }
}
