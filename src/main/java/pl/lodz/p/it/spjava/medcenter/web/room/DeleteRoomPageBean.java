package pl.lodz.p.it.spjava.medcenter.web.room;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import pl.lodz.p.it.spjava.medcenter.endpoint.RoomEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Room;

@Named(value = "deleteRoomPageBean")
@RequestScoped
public class DeleteRoomPageBean {

    public DeleteRoomPageBean() {
    }

    @EJB
    private RoomEndpoint roomEndpoint;

    public String deleteRoom(Room room) {
        roomEndpoint.deleteRoom(room);
        return "deleteRoomSuccess";
    }

}
