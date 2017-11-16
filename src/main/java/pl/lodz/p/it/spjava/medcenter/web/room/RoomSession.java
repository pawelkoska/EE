package pl.lodz.p.it.spjava.medcenter.web.room;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import pl.lodz.p.it.spjava.medcenter.dto.RoomDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.RoomEndpoint;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.model.Room;

@Named(value = "roomSession")
@SessionScoped
public class RoomSession implements Serializable {

    @EJB
    private RoomEndpoint roomEndpoint;

    private Room editingRoom;
    private Room deletedRoom;

    public Room getDeletedRoom() {
        return deletedRoom;
    }

    public void createRoom(RoomDTO room) throws AppBaseException{
        roomEndpoint.createRoom(room);
    }

    public List<Room> getAllRooms() {
        return roomEndpoint.getAllRooms();
    }

    public Room getEditingRoom() {
        return editingRoom;
    }

    public void saveEditedRoom()throws AppBaseException {
        if (null == editingRoom) {
            throw new IllegalArgumentException("Proba edytacji z pominieciem formularza");
        }
        roomEndpoint.saveEditedCategory(editingRoom);
        editingRoom = null;
    }

    public void getRoomToEdit(Room room) {
        editingRoom = roomEndpoint.getRoomToEdit(room);
    }
}
