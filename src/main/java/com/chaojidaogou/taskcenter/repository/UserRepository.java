package com.chaojidaogou.taskcenter.repository;

import com.chaojidaogou.taskcenter.domain.usergroup.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2015/1/19.
 */
public interface UserRepository extends UserRepositoryCustom, JpaRepository<User, Long> {
}
