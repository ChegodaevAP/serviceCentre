package ServiceCentre.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ServiceCentre.dao.DefectDao;
import ServiceCentre.model.Defect;
import ServiceCentre.service.DefectService;

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
