package org.zkoss.reference.developer.spring.security.service;


import org.zkoss.reference.developer.spring.security.model.Defect;

import java.util.List;

public interface DefectService {

    List<Defect> getAllDefect();

    Defect addNewDefect(Defect defect);
}
