package org.zkoss.reference.developer.spring.security.dao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.reference.developer.spring.security.model.Request;
import org.zkoss.reference.developer.spring.security.model.StatusHistory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RequestDao {
    @PersistenceContext
    private EntityManager entityManager;

    //TODO: Переписать метод слишком много обращений к бд все должно происходить за 1 запрос
    @Transactional(readOnly = true)
    public List<Request> getAll() {
        Query query = entityManager.createQuery("select o from Request as o");
        List<Request> result = query.getResultList();
        if (result != null) {
            for (Request request : result) {
                Hibernate.initialize(request.getMovementHistory());
                Hibernate.initialize(request.getStatusHistories());
                Hibernate.initialize(request.getClient());
                Hibernate.initialize(request.getRequestUsers());
                for (StatusHistory statusHistory : request.getStatusHistories()) {
                    Hibernate.initialize(statusHistory.getStatus());
                }

            }
        }
        return result;
    }

    @Transactional(readOnly = true)
    public Request findRequestById(Integer id) {
        Request request;
        try {
            Query query = entityManager.createQuery("select o from Request as o where o.id =:id")
                    .setParameter("id", id);
            request = (Request) query.getSingleResult();
            if (request != null) {
                Hibernate.initialize(request.getMovementHistory());
            }
        } catch (Exception e) {
            return null;
        }
        return request;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Request insertRequest(Request request) {
        entityManager.persist(request);
        entityManager.merge(request);
        return request;
    }
}
