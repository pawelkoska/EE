package pl.lodz.p.it.spjava.medcenter.web.appointment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.dto.AppointmentDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.AccountEndpoint;
import pl.lodz.p.it.spjava.medcenter.endpoint.AppointmentEndpoint;
import pl.lodz.p.it.spjava.medcenter.facade.AccountFacade;
import pl.lodz.p.it.spjava.medcenter.model.Account;
import pl.lodz.p.it.spjava.medcenter.model.Appointment;
import pl.lodz.p.it.spjava.medcenter.model.Examination;
import pl.lodz.p.it.spjava.medcenter.model.Patient;
import pl.lodz.p.it.spjava.medcenter.web.account.AccountSession;
import pl.lodz.p.it.spjava.medcenter.web.examination.ExaminationSession;

@Named(value = "findAppointmentPageBean")
@SessionScoped
public class FindAppointmentPageBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(FindAppointmentPageBean.class.getName());

    public FindAppointmentPageBean() {
    }

    @Inject
    private AppointmentSession appointmentSession;

    @Inject
    private ExaminationSession examinationSession;

    @Inject
    private AccountSession accountSession;

    @EJB
    private AppointmentEndpoint appointmentEndpoint;

    @EJB
    private AccountEndpoint accountEndpoint;

    @EJB
    private AccountFacade accountFacade;

    private List<Appointment> appointmentResult;
    private AppointmentDTO appointmentDto = new AppointmentDTO();
    private Appointment updatingAppointment;

//    public void setAppointmentResultList() {
//        appointmentResult = new ArrayList<>();
//        appointmentResult = appointmentEndpoint.matchAppointments(appointmentDto);       
//    }
    public void setAppointmentResultList() {
        for (Examination e : examinationSession.getAllExaminations()) {
            if (e.getName().equals(appointmentDto.getExamination())) {
                appointmentResult = appointmentEndpoint.matchAppointments(e);
            }
        }
    }

    public String makeAnAppointment(Appointment appointment) {
        String userName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();

        updatingAppointment = appointmentEndpoint.getUpdatingAppointment(appointment);

        for (Patient p : accountSession.getAllPatients()) {
            if (p.getLogin().equals(userName)) {
                LOG.log(Level.SEVERE, p.toString());
                updatingAppointment.setPatientId(p);
                appointmentEndpoint.updateAppointment(updatingAppointment);
//                LOG.log(Level.SEVERE, appointment.toString());
            }
        }
        return "makeAnAppointmentSuccess";
    }

    public List<Appointment> getAppointmentResult() {
        return appointmentResult;
    }

    public AppointmentDTO getAppointmentDto() {
        return appointmentDto;
    }

    public void setAppointmentDto(AppointmentDTO appointmentDto) {
        this.appointmentDto = appointmentDto;
    }

}
