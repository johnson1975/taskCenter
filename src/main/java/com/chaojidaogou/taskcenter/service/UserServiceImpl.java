package com.chaojidaogou.taskcenter.service;

import com.chaojidaogou.taskcenter.domain.usergroup.User;
import com.chaojidaogou.taskcenter.domain.usergroup.UserGroup;
import com.chaojidaogou.taskcenter.repository.UserGroupRepository;
import com.chaojidaogou.taskcenter.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Administrator on 2015/1/19.
 */
@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Inject
    private UserRepository userRepository;
    @Inject
    private UserGroupRepository userGroupRepository;

    @Override
    public List<User> getUsers(Long groupId) {
        UserGroup group = userGroupRepository.findOne(groupId);
        return userRepository.findByGroupExpression(group.getConditionExpression());
    }

    @Override
    public List<UserGroup> getUserGroups() {
        return userGroupRepository.findAll();
    }
}
