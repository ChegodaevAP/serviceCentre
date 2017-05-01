package ServiceCentre.serviceImpl;

import ServiceCentre.model.MovementHistory;
import ServiceCentre.model.Place;
import ServiceCentre.model.Status;
import ServiceCentre.model.StatusHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ServiceCentre.dao.PlaceDao;
import ServiceCentre.dao.StatusDao;
import ServiceCentre.service.DirectoryService;

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
