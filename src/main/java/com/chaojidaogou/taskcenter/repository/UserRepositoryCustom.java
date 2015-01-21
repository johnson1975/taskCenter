package com.chaojidaogou.taskcenter.repository;

import com.chaojidaogou.taskcenter.domain.usergroup.User;

import java.util.List;

/**
 * Created by Administrator on 2015/1/19.
 */
public interface UserRepositoryCustom {
    public List<User> findByGroupExpression(String groupExpression);
}
