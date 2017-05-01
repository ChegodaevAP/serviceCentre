package ServiceCentre.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ServiceCentre.model.MovementHistory;
import ServiceCentre.model.Place;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PlaceDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Place> getAllPlaces() {
        Query query = entityManager.createQuery("select p from Place as p");
        return query.getResultList();
    }

    @Transactional
    public Place insert(Place place) {
        entityManager.persist(place);
        entityManager.merge(place);
        return place;
    }

    @Transactional
    public Place findPlace(String address) {
        Query query = entityManager.createQuery("select p from Place as p where p.address=:address")
                .setParameter("address", address);
        Place place;
        try {
            place = (Place) query.getSingleResult();
        }catch (Exception e){
            return null;
        }
        return place;
    }

    @Transactional
    public MovementHistory insertInToMovementHistory(MovementHistory movementHistory){
        entityManager.persist(movementHistory);
        entityManager.merge(movementHistory);
        return movementHistory;
    }

}
