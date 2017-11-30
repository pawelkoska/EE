package pl.lodz.p.it.spjava.medcenter.endpoint;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.lodz.p.it.spjava.medcenter.dto.RoomDTO;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.exception.GeneralOptimisticLockException;
import pl.lodz.p.it.spjava.medcenter.exception.RoomException;
import pl.lodz.p.it.spjava.medcenter.facade.ExaminationFacade;
import pl.lodz.p.it.spjava.medcenter.facade.RoomFacade;
import pl.lodz.p.it.spjava.medcenter.interceptor.LoggingInterceptor;
import pl.lodz.p.it.spjava.medcenter.model.Examination;
import pl.lodz.p.it.spjava.medcenter.model.Room;
import pl.lodz.p.it.spjava.medcenter.model.utils.ContextUtils;

/**
 *
 * @author pawkos
 */
@Stateful
@RolesAllowed({"Reception", "Doctor"})
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class RoomEndpoint {

    @EJB
    private RoomFacade roomFacade;

    @EJB
    private ExaminationFacade examinationFacade;

    public void createRoom(RoomDTO room) throws AppBaseException {

        List<Examination> examinations = examinationFacade.findAll();
        Examination selectedExamination = null;
        for (Examination e : examinations) {
            if (e.getName().equals(room.getExaminationType())) {
                selectedExamination = e;
                break;
            }
        }
        if (selectedExamination == null) {
            throw new IllegalArgumentException("Błędna nazwa kategorii");
        }

        Room roomEntity = new Room();
        roomEntity.setRoomNumber(room.getRoomNumber());
        roomEntity.setExaminationType(selectedExamination);
        try {
            roomFacade.create(roomEntity);
            ContextUtils.emitSuccessMessage("examinationList");
        } catch (RoomException re) {
            ContextUtils.emitInternationalizedMessage(null, RoomException.KEY_DB_CONSTRAINT);
        }

    }

    public List<Room> getAllRooms() {
        return roomFacade.findAll();
    }

    public Room getRoomToEdit(Room room) {
        Room roomEntity = roomFacade.find(room.getId());
        return roomEntity;
    }

    public void saveEditedCategory(Room r) throws AppBaseException {
        try {
            roomFacade.edit(r);
            ContextUtils.emitSuccessMessage("");
        } catch (GeneralOptimisticLockException ce) {
            ContextUtils.emitInternationalizedMessage(null, GeneralOptimisticLockException.KEY_OPTIMISTIC_LOCK);
        }
    }

    public String deleteRoom(Room room) {
        roomFacade.remove(room);
        return "deleteRoomSuccess";
    }

}
