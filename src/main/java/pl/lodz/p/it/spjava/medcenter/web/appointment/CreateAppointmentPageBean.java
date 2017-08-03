package pl.lodz.p.it.spjava.medcenter.web.appointment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.dto.AppointmentDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.AppointmentEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Appointment;

@Named(value = "createAppointmentPageBean")
@RequestScoped
public class CreateAppointmentPageBean {

    public CreateAppointmentPageBean() {
    }

    @EJB
    private AppointmentEndpoint appointmentEndpoint;

    @Inject
    private AppointmentSession appointmentSession;

    private List<Appointment> appointmentObjList = new ArrayList<>();
    
    private AppointmentDTO appointmentDto = new AppointmentDTO();


    public String createAppointment() throws ParseException {
        return appointmentSession.createAppointment(appointmentDto);
    }

    @PostConstruct
    public void getAllCategories() {
        List<Appointment> allAppointments = appointmentEndpoint.getAllAppointments();
        for (Appointment a : allAppointments) {
            appointmentObjList.add(a);
        }
    }

    public AppointmentEndpoint getAppointmentEndpoint() {
        return appointmentEndpoint;
    }

    public void setAppointmentEndpoint(AppointmentEndpoint appointmentEndpoint) {
        this.appointmentEndpoint = appointmentEndpoint;
    }

    public AppointmentSession getAppointmentSession() {
        return appointmentSession;
    }

    public void setAppointmentSession(AppointmentSession appointmentSession) {
        this.appointmentSession = appointmentSession;
    }

    public List<Appointment> getAppointmentObjList() {
        return appointmentObjList;
    }

    public void setAppointmentObjList(List<Appointment> appointmentObjList) {
        this.appointmentObjList = appointmentObjList;
    }

    public AppointmentDTO getAppointmentDto() {
        return appointmentDto;
    }

    public void setAppointmentDto(AppointmentDTO appointmentDto) {
        this.appointmentDto = appointmentDto;
    }



}
