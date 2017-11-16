package pl.lodz.p.it.spjava.medcenter.web.appointment;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import pl.lodz.p.it.spjava.medcenter.endpoint.AppointmentEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Appointment;

@Named(value = "deleteAppointmentPageBean")
@RequestScoped
public class DeleteAppointmentPageBean {

    public DeleteAppointmentPageBean() {
    }

    @EJB
    private AppointmentEndpoint appointmentEndpoint;


    public String deleteAppointment(Appointment appointment) {
        appointmentEndpoint.deleteAppointment(appointment);
        return "allAppointments";
    }

}
