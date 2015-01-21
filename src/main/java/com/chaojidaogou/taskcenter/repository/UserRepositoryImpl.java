package com.chaojidaogou.taskcenter.repository;

import com.chaojidaogou.taskcenter.domain.usergroup.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Administrator on 2015/1/19.
 */
public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findByGroupExpression(String groupExpression) {
        return em.createQuery("select u from User u where " + groupExpression).getResultList();
    }
}
