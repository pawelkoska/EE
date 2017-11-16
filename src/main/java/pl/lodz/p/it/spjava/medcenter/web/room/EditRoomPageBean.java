package pl.lodz.p.it.spjava.medcenter.web.room;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.endpoint.RoomEndpoint;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.model.Room;

@Named(value = "editRoomManageBean")
@RequestScoped
public class EditRoomPageBean {

    public EditRoomPageBean() {
    }

    @EJB
    private RoomEndpoint roomEndpoint;

    @Inject
    private RoomSession roomSession;

    public String getRoomToEdit(Room room) {
        roomSession.getRoomToEdit(room);
        return "editRoom";
    }

    public void saveEditedRoom()throws AppBaseException {
        roomSession.saveEditedRoom();
    }

    public Room getEditingRoom() {
        return roomSession.getEditingRoom();
    }


}
