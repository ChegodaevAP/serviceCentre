package ServiceCentre.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ServiceCentre.model.Defect;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DefectDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Defect> getAll() {
        Query query = entityManager.createQuery("select d from Defect as d");
        List<Defect> result = query.getResultList();
        return result;
    }

    @Transactional
    public Defect insert(Defect defect) {
        entityManager.persist(defect);
        entityManager.merge(defect);
        return defect;
    }
}
