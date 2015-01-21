package com.chaojidaogou.taskcenter.repository;

import com.chaojidaogou.taskcenter.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/1/20.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT * FROM Task WHERE taskId in ?1 ORDER BY FIELD(taskId, ?1)", nativeQuery = true)
    List<Task> findByTaskIdIn(Collection<Long> taskIdList);
}
