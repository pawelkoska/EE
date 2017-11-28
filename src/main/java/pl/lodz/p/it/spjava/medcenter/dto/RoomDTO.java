package pl.lodz.p.it.spjava.medcenter.dto;

/**
 *
 * @author pawkos
 */
public class RoomDTO {

    public RoomDTO() {
    }

    private String roomNumber;
    private String examinationType;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getExaminationType() {
        return examinationType;
    }

    public void setExaminationType(String examinationType) {
        this.examinationType = examinationType;
    }

}
