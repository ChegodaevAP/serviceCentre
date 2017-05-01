package ServiceCentre.service;


import ServiceCentre.model.Defect;

import java.util.List;

public interface DefectService {

    List<Defect> getAllDefect();

    Defect addNewDefect(Defect defect);
}
