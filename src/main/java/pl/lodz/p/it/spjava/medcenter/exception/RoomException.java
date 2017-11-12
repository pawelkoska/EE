/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.it.spjava.medcenter.exception;
import pl.lodz.p.it.spjava.medcenter.model.Examination;
import pl.lodz.p.it.spjava.medcenter.model.Room;

/**
 *
 * @author pawkos
 */
public class RoomException extends AppBaseException {

    static final public String KEY_DB_CONSTRAINT = "error.room.db.unique.name";

    private RoomException(String message) {
        super(message);
    }

    private RoomException(String message, Throwable cause) {
        super(message, cause);
    }
    
    private Room room;

    public Room getRoom() {
        return room;
    }

    static public RoomException createWithDbCheckConstraintKey(Room room, Throwable cause) {
        RoomException ze = new RoomException(KEY_DB_CONSTRAINT, cause);
        ze.room=room;
        return ze;
    }
}
