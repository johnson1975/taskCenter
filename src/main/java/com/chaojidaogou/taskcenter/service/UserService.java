package com.chaojidaogou.taskcenter.service;

import com.chaojidaogou.taskcenter.domain.usergroup.User;
import com.chaojidaogou.taskcenter.domain.usergroup.UserGroup;

import java.util.List;

/**
 * Created by Administrator on 2015/1/19.
 */
public interface UserService {
    public List<User> getUsers(Long groupId);

    public List<UserGroup> getUserGroups();

}
