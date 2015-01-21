package com.chaojidaogou.taskcenter.domain.task;

import com.chaojidaogou.taskcenter.common.Keys;
import com.chaojidaogou.taskcenter.domain.usergroup.User;
import com.chaojidaogou.taskcenter.domain.usergroup.UserGroup;
import com.chaojidaogou.taskcenter.repository.RedisRepository;
import com.chaojidaogou.taskcenter.repository.UserGroupRepository;
import com.chaojidaogou.taskcenter.repository.UserRepository;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/1/20.
 */
@Component(value = "taskScheduler")
public class TaskManager {
    private static final List<Task> SCHEDULED_TASKS = new ArrayList<>();
    private static final TaskScheduler SCHEDULER = new ConcurrentTaskScheduler();
    @Inject
    private UserRepository userRepository;
    @Inject
    private UserGroupRepository userGroupRepository;
    @Inject
    private RedisRepository redisRepository;

    private List<User> getUsers(Long groupId) {
        UserGroup group = userGroupRepository.findOne(groupId);
        return userRepository.findByGroupExpression(group.getConditionExpression());
    }

    private Long getTaskId(Long uid) {
        return redisRepository.increment(Keys.globalTaskIdOfUid(uid));
    }

    private void putTaskIntoInbox(User user, Task task) {
        redisRepository.add(user.getInbox(), task.getTaskId().toString());
    }

    public void schedule(final Task task) {
        SCHEDULER.schedule(new Runnable() {
            @Override
            public void run() {
                for (User user : getUsers(task.getUserGroup())) {
                    putTaskIntoInbox(user, task);
                }
            }
        }, new CronTrigger(task.getScheduleExpression()));
        SCHEDULED_TASKS.add(task);
    }
}
