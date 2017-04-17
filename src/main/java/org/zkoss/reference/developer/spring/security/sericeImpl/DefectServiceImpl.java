package org.zkoss.reference.developer.spring.security.sericeImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.reference.developer.spring.security.dao.DefectDao;
import org.zkoss.reference.developer.spring.security.model.Defect;
import org.zkoss.reference.developer.spring.security.service.DefectService;

import java.util.List;

@Service("defectService")
public class DefectServiceImpl implements DefectService {

    @Autowired
    DefectDao defectDao;

    @Override
    public List<Defect> getAllDefect() {
        return defectDao.getAll();
    }

    @Override
    public Defect addNewDefect(Defect defect) {
        return defectDao.insert(defect);
    }
}
