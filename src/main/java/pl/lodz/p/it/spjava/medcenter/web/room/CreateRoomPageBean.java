package pl.lodz.p.it.spjava.medcenter.web.room;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.dto.RoomDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.RoomEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Room;

@Named(value = "createRoomPageBean")
@RequestScoped
public class CreateRoomPageBean {

    public CreateRoomPageBean() {
    }

    @EJB
    private RoomEndpoint roomEndpoint;

    @Inject
    private RoomSession roomSession;

    private RoomDTO roomDTO = new RoomDTO();

    public String createRoom() {
        return roomSession.createRoom(roomDTO);
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO room) {
        this.roomDTO = room;
    }

}
