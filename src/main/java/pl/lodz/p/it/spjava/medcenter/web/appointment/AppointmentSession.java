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
import pl.lodz.p.it.spjava.medcenter.facade.AppointmentFacade;
import pl.lodz.p.it.spjava.medcenter.model.Appointment;

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

    public String createAppointment(AppointmentDTO appointment) {
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
