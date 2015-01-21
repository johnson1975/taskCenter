package com.chaojidaogou.taskcenter.repository;

import com.chaojidaogou.taskcenter.domain.usergroup.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2015/1/19.
 */
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
}
