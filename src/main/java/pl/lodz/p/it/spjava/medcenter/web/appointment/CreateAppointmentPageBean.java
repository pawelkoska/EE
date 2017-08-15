package pl.lodz.p.it.spjava.medcenter.web.appointment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.dto.AppointmentDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.AccountEndpoint;
import pl.lodz.p.it.spjava.medcenter.endpoint.AppointmentEndpoint;
import pl.lodz.p.it.spjava.medcenter.endpoint.ExaminationEndpoint;
import pl.lodz.p.it.spjava.medcenter.endpoint.RoomEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Appointment;
import pl.lodz.p.it.spjava.medcenter.model.Doctor;
import pl.lodz.p.it.spjava.medcenter.model.Examination;
import pl.lodz.p.it.spjava.medcenter.model.Room;

@Named(value = "createAppointmentPageBean")
@RequestScoped
public class CreateAppointmentPageBean {

    private static final Logger LOG = Logger.getLogger(CreateAppointmentPageBean.class.getName());

    
    
    public CreateAppointmentPageBean() {
    }

    @EJB
    private AppointmentEndpoint appointmentEndpoint;

    @EJB
    private ExaminationEndpoint examinationEndpoint;

    @EJB
    private RoomEndpoint roomEndpoint;

    @EJB
    private AccountEndpoint accountEndpoint;

    @Inject
    private AppointmentSession appointmentSession;
   
    private List<Appointment> appointmentObjList = new ArrayList<>();
    
    private Map<String, Map<String, String>> data = new HashMap<>();
    private Map<String, Map<String, String>> dataDoctor = new HashMap<>();
    
    private Map<String, String> examinations = new HashMap<>();

    
    private AppointmentDTO appointmentDto = new AppointmentDTO();
          
    public String createAppointment() {
        LOG.log(Level.INFO, appointmentDto.toString());
        return appointmentSession.createAppointment(appointmentDto);
    }
    
    @PostConstruct
    public void init() {
        List<Appointment> allAppointments = appointmentEndpoint.getAllAppointments();
        for (Appointment a : allAppointments) {
            appointmentObjList.add(a);
        }
        
        for (Examination e : examinationEndpoint.getAllExaminations()) {
            examinations.put(e.getName(), e.getName());
            Map<String, String> mapRoom = new HashMap<>();
            for (Room r : roomEndpoint.getAllRooms()) {
                if (r.getExaminationType().getName().equals(e.getName())) {
                    mapRoom.put(r.getRoomNumber(), r.getRoomNumber());
                }
            }
            Map<String, String> mapDoctor = new HashMap<>();
            for (Doctor d : accountEndpoint.getAllDoctors()) {
                if (d.getSpecialization().getName().equals(e.getCategoryId().getName())) {
                    mapDoctor.put(d.getSecondName(), d.getSecondName());
                }
            }
            data.put(e.getName(), mapRoom);
            dataDoctor.put(e.getName(), mapDoctor);
        }
    }

    public void onExaminationChange() {
        if ( appointmentDto.getExamination() != null && !appointmentDto.getExamination().equals("")) { 
            appointmentSession.setRooms(data.get(appointmentDto.getExamination()));
        } 
     
        if (appointmentDto.getExamination() != null && !appointmentDto.getExamination().equals("")) {
            appointmentSession.setDoctors(dataDoctor.get(appointmentDto.getExamination()));
        } 
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public Map<String, String> getFilteredRooms () {
        return appointmentSession.getRooms();
    }
    public Map<String, String> getFilteredDoctors () {
        return appointmentSession.getDoctors();
    }

    public Map<String, Map<String, String>> getDataDoctor() {
        return dataDoctor;
    }

    public Map<String, String> getExaminations() {
        return examinations;
    }

    public AppointmentDTO getAppointmentDto() {
        return appointmentDto;
    }

    public void setAppointmentDto(AppointmentDTO appointmentDto) {
        this.appointmentDto = appointmentDto;
    }

    public List<Appointment> getAppointmentObjList() {
        return appointmentObjList;
    }

    public void setAppointmentObjList(List<Appointment> appointmentObjList) {
        this.appointmentObjList = appointmentObjList;
    }

}
