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
public class OrderDao {
    @PersistenceContext
    private EntityManager entityManager;

    //TODO: Переписать метод слишком много обращений к бд все должно происходить за 1 запрос
    @Transactional(readOnly = true)
    public List<Request> getAll() {
        Query query = entityManager.createQuery("select o from Request as o");
        List<Request> result = query.getResultList();
        if (result != null) {
            for (Request order : result) {
                Hibernate.initialize(order.getMovementHistory());
                Hibernate.initialize(order.getStatusHistories());
                Hibernate.initialize(order.getClient());
                Hibernate.initialize(order.getOrderUsers());
                for (StatusHistory statusHistory : order.getStatusHistories()) {
                    Hibernate.initialize(statusHistory.getStatus());
                }

            }
        }
        return result;
    }

    @Transactional(readOnly = true)
    public Request findOrderById(Integer id) {
        Request order;
        try {
            Query query = entityManager.createQuery("select o from Request as o where o.id =:id")
                    .setParameter("id", id);
            order = (Request) query.getSingleResult();
            if (order != null) {
                Hibernate.initialize(order.getMovementHistory());
            }
        } catch (Exception e) {
            return null;
        }
        return order;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Request insertOrder(Request order) {
        entityManager.persist(order);
        entityManager.merge(order);
        return order;
    }
}
