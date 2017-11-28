package pl.lodz.p.it.spjava.medcenter.web.room;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.dto.RoomDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.RoomEndpoint;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;

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

    public void createRoom() throws AppBaseException {
        roomSession.createRoom(roomDTO);
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO room) {
        this.roomDTO = room;
    }

}
