package ServiceCentre.service;


import ServiceCentre.model.MovementHistory;
import ServiceCentre.model.Place;
import ServiceCentre.model.Status;
import ServiceCentre.model.StatusHistory;

import java.util.List;

/**
 * Сервис отвечающий за все справочники
 */
public interface DirectoryService {
    /**
     * Получить все места
     *
     * @return список мест
     */
    List<Place> getAllPlace();

    /**
     * ДОбавить новое место в БД
     *
     * @param place Сущность место
     * @return ту же сущность, но с идентефикатором как в БД
     */
    Place addNewPlace(Place place);

    Status addNewStatus(Status status);

    List<Status> getAllStatus();

    Status getDefaultStatusOnCreate();

    StatusHistory addNewStatusHistory(StatusHistory statusHistory);

    MovementHistory addNewMovementHistory(MovementHistory movementHistory);

}
