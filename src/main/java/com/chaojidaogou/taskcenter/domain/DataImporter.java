package com.chaojidaogou.taskcenter.domain;

import com.chaojidaogou.taskcenter.domain.usergroup.User;
import com.chaojidaogou.taskcenter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2015/1/19.
 */
@Component(value = "dataImporter")
public class DataImporter {
    @Inject
    private JdbcTemplate jdbcTemplate;
    @Value(value = "${user.import.sql}")
    private String importUserSQL;
    @Inject
    private UserRepository userRepository;

    @PostConstruct
    public void importUserData() {
        userRepository.save(jdbcTemplate.query(importUserSQL, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setGoldCoin(resultSet.getInt("gold"));
                user.setOrgId(resultSet.getInt("belong_org"));
                user.setUid(resultSet.getLong("sys_user_id"));
                user.setOrgName(resultSet.getString("org_name"));
                user.setExperience(resultSet.getInt("exp"));
                user.setEnergy(resultSet.getInt("curr_energy"));
                user.setEntryDate(resultSet.getTimestamp("entry_time"));
                user.setLevel(resultSet.getInt("level"));
                user.setPostId(resultSet.getInt("user_post"));
                user.setPostName(resultSet.getString("post_name"));
                user.setName(resultSet.getString("name"));
                user.setTelephone(resultSet.getString("tel"));
                return user;
            }
        }));
    }
}

/*

    private Long uid;
    private String name;
    private Integer orgId;
    private String orgName;
    private String telephone;
    private Integer postId;
    private String postName;
    private Integer goldCoin;
    private Integer experience;
    private Integer level;
    private Date birthday;
    private Date entryDate;
    private Integer gender;

select a.sys_user_id,
a.belong_org,
a.tel,
a.create_time,
a.user_post,
a.gold,
a.exp,
a.curr_energy,
a.`level`,
a.birthday,
a.entry_time,
a.gender,
b.org_name,
 */
