package org.zkoss.reference.developer.spring.security.sericeImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.reference.developer.spring.security.dao.PlaceDao;
import org.zkoss.reference.developer.spring.security.dao.StatusDao;
import org.zkoss.reference.developer.spring.security.model.*;
import org.zkoss.reference.developer.spring.security.service.DirectoryService;

import java.util.List;

@Service("directoryService")
public class DirectoryServiceImpl implements DirectoryService {

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private StatusDao statusDao;

    @Override
    public List<Place> getAllPlace() {
        return placeDao.getAllPlaces();
    }

    @Override
    public Place addNewPlace(Place place) {
        if (placeDao.findPlace(place.getAddress()) == null) {
            return placeDao.insert(place);
        } else return null;
    }

    //TODO: Отрефакторить вставку на всех сервисах
    @Override
    public Status addNewStatus(Status status) {
        if (statusDao.findStatus(status.getStatus()) == null) {
            return statusDao.insert(status);
        } else return null;
    }

    @Override
    public StatusHistory addNewStatusHistory(StatusHistory statusHistory) {
        return statusDao.insertInToStatusHistory(statusHistory);
    }

    @Override
    public MovementHistory addNewMovementHistory(MovementHistory movementHistory) {
        return placeDao.insertInToMovementHistory(movementHistory);
    }


    @Override
    public List<Status> getAllStatus() {
        return statusDao.getAllStatus();
    }

    @Override
    public Status getDefaultStatusOnCreate() {
        return statusDao.findStatusById(1);
    }
}
