package org.zkoss.reference.developer.spring.security.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.reference.developer.spring.security.model.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StatusDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Status> getAllStatus() {
        Query query = entityManager.createQuery("select p from Status as p");
        return query.getResultList();
    }

    @Transactional
    public Status insert(Status status) {
        entityManager.persist(status);
        entityManager.merge(status);
        return status;
    }

    @Transactional
    public Status findStatus(String stat) {
        Query query = entityManager.createQuery("select p from Place as p where p.address=:status")
                .setParameter("status", stat);
        Status status;
        try {
            status = (Status) query.getSingleResult();
        }catch (Exception e){
            return null;
        }
        return status;
    }
    @Transactional
    public Status findStatusById(Integer id) {
        Query query = entityManager.createQuery("select p from Place as p where p.id=:id")
                .setParameter("id", id);
        Status status;
        try {
            status = (Status) query.getSingleResult();
        }catch (Exception e){
            return null;
        }
        return status;
    }
}
