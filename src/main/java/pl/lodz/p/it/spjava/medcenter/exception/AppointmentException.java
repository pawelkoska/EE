/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.it.spjava.medcenter.exception;

import pl.lodz.p.it.spjava.medcenter.model.Appointment;

/**
 *
 * @author pawkos
 */
public class AppointmentException extends AppBaseException {

    static final public String KEY_ACC_NOT_CONFIRMED = "error.account.not.confirmed";
    static final public String KEY_APPOINTMENT_INVALID_TIMESLOT = "error.appointment.invalid.timeslot";
    static final public String KEY_DB_CONSTRAINT = "error.appointment.db.unique.name";

    private AppointmentException(String message) {
        super(message);
    }

    private AppointmentException(String message, Throwable cause) {
        super(message, cause);
    }
    
    private Appointment appointment;

    public Appointment getAppointment() {
        return appointment;
    }

    static public AppointmentException reserveAppointmentAccountNotConfirmed(Appointment appointment) {
        AppointmentException ze = new AppointmentException(KEY_ACC_NOT_CONFIRMED);
        ze.appointment=appointment;
        return ze;
    }
    
    static public AppointmentException createWithDbCheckConstraintKey(Appointment appointment, Throwable cause) {
        AppointmentException ze = new AppointmentException(KEY_DB_CONSTRAINT, cause);
        ze.appointment=appointment;
        return ze;
    }
    
    static public AppointmentException createWithInvalidTimeSlots() {
        AppointmentException ze = new AppointmentException(KEY_DB_CONSTRAINT);
        return ze;
    }
}
