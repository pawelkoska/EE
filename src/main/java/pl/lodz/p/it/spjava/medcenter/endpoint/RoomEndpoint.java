package pl.lodz.p.it.spjava.medcenter.endpoint;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.lodz.p.it.spjava.medcenter.dto.RoomDTO;
import pl.lodz.p.it.spjava.medcenter.facade.CategoryFacade;
import pl.lodz.p.it.spjava.medcenter.facade.ExaminationFacade;
import pl.lodz.p.it.spjava.medcenter.facade.RoomFacade;
import pl.lodz.p.it.spjava.medcenter.interceptor.LoggingInterceptor;
import pl.lodz.p.it.spjava.medcenter.model.Category;
import pl.lodz.p.it.spjava.medcenter.model.Examination;
import pl.lodz.p.it.spjava.medcenter.model.Room;

/**
 *
 * @author pawkos
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class RoomEndpoint {

    @EJB
    private RoomFacade roomFacade;
    
    @EJB
    private ExaminationFacade examinationFacade;

    public void createRoom(RoomDTO room) {

        List<Examination> examinations = examinationFacade.findAll();
        Examination selectedExamination = null;
        for (Examination e : examinations) {
            if (e.getName().equals(room.getExaminationType())) {
                selectedExamination = e;
                break;
            }
        }
        if (selectedExamination == null) {
            throw new IllegalArgumentException("Błędna nazwa kategorii"); //TODO: w tym miejscu wymagane zgłoszenie wyjątku aplikacyjnego
        }

        Room roomEntity = new Room();
        roomEntity.setRoomNumber(room.getRoomNumber());
        roomEntity.setExaminationType(selectedExamination);
        roomFacade.create(roomEntity);
    }

    public List<Room> getAllRooms() {
        return roomFacade.findAll();
    }

    public Room getRoomToEdit(Room room) {
        Room roomEntity = roomFacade.find(room.getId());
//        kontoFacade.refresh(encja);
        return roomEntity;
    }

    public void saveEditedCategory(Room r) {
        roomFacade.edit(r);
    }

    public String deleteRoom(Room room) {
        roomFacade.remove(room);
        return "deleteRoomSuccess";
    }

}