package pl.lodz.p.it.spjava.medcenter.web.appointment;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import pl.lodz.p.it.spjava.medcenter.dto.AppointmentDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.AppointmentEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Appointment;

@Named(value = "appointmentSession")
@SessionScoped
public class AppointmentSession implements Serializable {

    @EJB
    private AppointmentEndpoint appointmentEndpoint;

    private Appointment editingAppointment;
    private Appointment deletedAppointment;

    public Appointment getDeletedAppointment() {
        return deletedAppointment;
    }

    public String createAppointment(AppointmentDTO appointment) throws ParseException {
        appointmentEndpoint.createAppointment(appointment);
        return "createAppointmentSuccess";
    }

    public List<Appointment> getAllAppointments() {
        return appointmentEndpoint.getAllAppointments();
    }

    public Appointment getEditingAppointment() {
        return editingAppointment;
    }

    public void saveEditedAppointment() {
        if (null == editingAppointment) {
            throw new IllegalArgumentException("Proba edytacji z pominieciem formularza");
        }
        appointmentEndpoint.saveEditedAppointment(editingAppointment);
        editingAppointment = null;
    }

    public void getAppointmentToEdit(Appointment appointment) {
        editingAppointment = appointmentEndpoint.getAppointmentToEdit(appointment);
    }
}
