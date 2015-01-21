package com.chaojidaogou.taskcenter.domain.usergroup;


import com.chaojidaogou.taskcenter.common.Keys;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2015/1/16.
 */
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = -5286049026878184497L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String name;
    private Integer orgId;
    private String orgName;
    private String telephone;
    private Integer postId;
    private String postName;
    private Integer goldCoin;
    private Integer experience;
    private Integer energy;
    private Integer level;
    private Date birthday;
    private Timestamp entryDate;
    private Integer gender;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Integer getGoldCoin() {
        return goldCoin;
    }

    public void setGoldCoin(Integer goldCoin) {
        this.goldCoin = goldCoin;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Timestamp getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Timestamp entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    @Transient
    public String getInbox() {
        return Keys.uidInboxQueue(uid);
    }
}
