package pl.lodz.p.it.spjava.medcenter.web.appointment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.dto.AppointmentDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.AppointmentEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Appointment;
import pl.lodz.p.it.spjava.medcenter.model.Examination;
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
    
    @EJB
    private AppointmentEndpoint appointmentEndpoint;

    private List<Appointment> appointmentResult;
    private AppointmentDTO appointmentDto = new AppointmentDTO();

//    public void setAppointmentResultList() {
//        appointmentResult = new ArrayList<>();
//        appointmentResult = appointmentEndpoint.matchAppointments(appointmentDto);       
//    }
    
    
    public void setAppointmentResultList(){
        for (Examination e : examinationSession.getAllExaminations()) {
            if(e.getName().equals(appointmentDto.getExamination())){
                appointmentResult = appointmentEndpoint.matchAppointments(e);
            }
        }
    }
    
    public void makeAnAppointment () {
        
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
