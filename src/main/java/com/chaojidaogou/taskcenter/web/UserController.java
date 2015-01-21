package com.chaojidaogou.taskcenter.web;

import com.chaojidaogou.taskcenter.domain.usergroup.User;
import com.chaojidaogou.taskcenter.domain.usergroup.UserGroup;
import com.chaojidaogou.taskcenter.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Administrator on 2015/1/19.
 */
@Controller(value = "userController")
public class UserController {
    @Inject
    private UserService userService;

    @RequestMapping(value = "/group/{groupId}/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getGroupUsers(@PathVariable Long groupId) {
        return userService.getUsers(groupId);
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    @ResponseBody
    public List<UserGroup> getUserGroups() {
        return userService.getUserGroups();
    }
}
