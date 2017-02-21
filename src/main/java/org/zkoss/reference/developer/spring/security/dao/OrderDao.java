package org.zkoss.reference.developer.spring.security.dao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.reference.developer.spring.security.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Order> getAll() {
        Query query = entityManager.createQuery("select o from Order as o");
        List<Order> result = query.getResultList();
        return result;
    }

    @Transactional(readOnly = true)
    public Order findOrderById(Integer id) {
        Order order;
        try {
            Query query = entityManager.createQuery("select o from Order as o where o.id =:id")
                    .setParameter("id", id);
            order = (Order) query.getSingleResult();
            if (order != null) {
                Hibernate.initialize(order.getMuvementHistory());
            }
        } catch (Exception e) {
            return null;
        }
        return order;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Order insertOrder(Order order) {
        entityManager.persist(order);
        entityManager.merge(order);
        return order;
    }
}
