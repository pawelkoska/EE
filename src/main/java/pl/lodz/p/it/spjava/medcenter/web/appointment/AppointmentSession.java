package pl.lodz.p.it.spjava.medcenter.web.appointment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import pl.lodz.p.it.spjava.medcenter.dto.AppointmentDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.AppointmentEndpoint;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.model.Appointment;
import pl.lodz.p.it.spjava.medcenter.model.Patient;

@Named(value = "appointmentSession")
@SessionScoped
public class AppointmentSession implements Serializable {

    @EJB
    private AppointmentEndpoint appointmentEndpoint;

    private Appointment editingAppointment;
    private Appointment deletedAppointment;

    private Map<String, String> rooms;
    private Map<String, String> doctors;
    
    private List<String> doctorsNamesList;
    
    private List<Appointment> myAppointments = new ArrayList<>();

    public List<Appointment> getMyAppointments() {
        return myAppointments;
    }

    public void setMyAppointments(List<Appointment> myAppointments) {
        this.myAppointments = myAppointments;
    }
    
    public Appointment getDeletedAppointment() {
        return deletedAppointment;
    }

    public void createAppointment(AppointmentDTO appointment) throws AppBaseException{
        appointmentEndpoint.createAppointment(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentEndpoint.getAllAppointments();
    }

    public Appointment getEditingAppointment() {
        editingAppointment.setPatientId(new Patient());
        return editingAppointment;
    }

    public void saveEditedAppointment()throws AppBaseException {
        if (null == editingAppointment) {
//            throw new IllegalArgumentException("Proba edytacji z pominieciem formularza");
        }
        appointmentEndpoint.saveEditedAppointment(editingAppointment);
        editingAppointment = null;
    }

    public void getAppointmentToEdit(Appointment appointment) {
        editingAppointment = appointmentEndpoint.getAppointmentToEdit(appointment);
    }

    public Map<String, String> getRooms() {
        return rooms;
    }

    public void setRooms(Map<String, String> rooms) {
        this.rooms = rooms;
    }

    public Map<String, String> getDoctors() {
        return doctors;
    }
    
    public List<String> getDoctorsNamesList() {
        doctorsNamesList = new ArrayList<>(doctors.values());
        return doctorsNamesList;
    }

    public void setDoctors(Map<String, String> doctors) {
        this.doctors = doctors;
    }


    
    
}
