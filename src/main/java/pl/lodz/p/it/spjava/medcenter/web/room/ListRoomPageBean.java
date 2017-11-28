package pl.lodz.p.it.spjava.medcenter.web.room;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import pl.lodz.p.it.spjava.medcenter.endpoint.RoomEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Room;

@Named(value = "listRoomPageBean")
@RequestScoped
public class ListRoomPageBean {

    public ListRoomPageBean() {
    }

    @EJB
    private RoomEndpoint roomEndpoint;

    private List<Room> roomObjList = new ArrayList<>();
    private List<String> roomNumberList = new ArrayList<>();

    @PostConstruct
    public void getAllRooms() {
        List<Room> allRooms = roomEndpoint.getAllRooms();
        for (Room room : allRooms) {
            roomObjList.add(room);
            roomNumberList.add(room.getRoomNumber());
        }
    }

    public List<Room> getRoomObjList() {
        return roomObjList;
    }

    public void setRoomObjList(List<Room> roomObjList) {
        this.roomObjList = roomObjList;
    }

    public List<String> getRoomNumberList() {
        return roomNumberList;
    }

    public void setRoomNumberList(List<String> roomNumberList) {
        this.roomNumberList = roomNumberList;
    }

}
