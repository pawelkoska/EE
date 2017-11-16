package pl.lodz.p.it.spjava.medcenter.web.appointment;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.dto.AppointmentDTO;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.model.Appointment;

@Named(value = "editAppointmentManageBean")
@RequestScoped
public class EditAppointmentPageBean {

    public EditAppointmentPageBean() {
        appointmentDto = new AppointmentDTO();
    }

    @Inject
    private AppointmentSession appointmentSession;

    private AppointmentDTO appointmentDto;

    public AppointmentSession getAppointmentSession() {
        return appointmentSession;
    }

    public void setAppointmentSession(AppointmentSession appointmentSession) {
        this.appointmentSession = appointmentSession;
    }

    public AppointmentDTO getAppointmentDto() {
        return appointmentDto;
    }

    public void setAppointmentDto(AppointmentDTO appointmentDto) {
        this.appointmentDto = appointmentDto;
    }

    public String getAppointmentToEdit(Appointment appointment) {
        appointmentSession.getAppointmentToEdit(appointment);
        return "editAppointment";
    }

    public void saveEditedAppointment() throws AppBaseException {
        appointmentSession.saveEditedAppointment();
    }

    public Appointment getEditingAppointment() {
        return appointmentSession.getEditingAppointment();
    }

}
