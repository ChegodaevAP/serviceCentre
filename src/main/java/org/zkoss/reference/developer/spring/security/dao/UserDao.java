package org.zkoss.reference.developer.spring.security.dao;

import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.reference.developer.spring.security.model.User;
import org.zkoss.reference.developer.spring.security.model.UserProfile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<User> getAll() {
        Query query = entityManager.createQuery("select u from User as u");
        List<User> result = query.getResultList();
        return result;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public User insertUser(User user) {
        entityManager.persist(user);
        entityManager.merge(user);
        return user;
    }

    @Transactional(readOnly = true)
    public User findUserByName(String userName) throws UsernameNotFoundException {
        Query query = entityManager.createQuery("select u from User as u where u.name =:userName")
                .setParameter("userName", userName);
        User user = (User) query.getSingleResult();
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    @Transactional(readOnly = true)
    public UserProfile findProfile(String type) {
        Query query = entityManager.createQuery("select up from UserProfile as up where up.type =:profileType")
                .setParameter("profileType", type);
        UserProfile userProfile = (UserProfile) query.getSingleResult();
        if (userProfile != null) {
            Hibernate.initialize(userProfile.getType());
        }
        return userProfile;
    }

    @Transactional(readOnly = true)
    public List<UserProfile> getAllUserProfile(){
        Query query = entityManager.createQuery("select up from UserProfile as up");
        return query.getResultList();
    }
}
