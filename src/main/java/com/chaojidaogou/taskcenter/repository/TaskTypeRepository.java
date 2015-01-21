package com.chaojidaogou.taskcenter.repository;

import com.chaojidaogou.taskcenter.domain.task.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2015/1/20.
 */
public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {
}
