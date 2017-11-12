package pl.lodz.p.it.spjava.medcenter.endpoint;

import java.util.Date;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import pl.lodz.p.it.spjava.medcenter.dto.AppointmentDTO;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.exception.AppointmentException;
import pl.lodz.p.it.spjava.medcenter.exception.CategoryException;
import pl.lodz.p.it.spjava.medcenter.facade.AccountFacade;
import pl.lodz.p.it.spjava.medcenter.facade.AppointmentFacade;
import pl.lodz.p.it.spjava.medcenter.facade.CategoryFacade;
import pl.lodz.p.it.spjava.medcenter.facade.ExaminationFacade;
import pl.lodz.p.it.spjava.medcenter.facade.RoomFacade;
import pl.lodz.p.it.spjava.medcenter.interceptor.LoggingInterceptor;
import pl.lodz.p.it.spjava.medcenter.model.Appointment;
import pl.lodz.p.it.spjava.medcenter.model.Doctor;
import pl.lodz.p.it.spjava.medcenter.model.Examination;
import pl.lodz.p.it.spjava.medcenter.model.Room;
import pl.lodz.p.it.spjava.medcenter.model.utils.ContextUtils;
import pl.lodz.p.it.spjava.medcenter.web.account.AccountSession;

/**
 *
 * @author pawkos
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class AppointmentEndpoint {

    @EJB
    private AppointmentFacade appointmentFacade;

    @EJB
    private CategoryFacade categoryFacade;

    @EJB
    private AccountFacade accountFacade;

    @EJB
    private RoomFacade roomFacade;

    @EJB
    private ExaminationFacade examinationFacade;
    
    @Inject
    private AccountSession accountSession;
    
    @Resource
    protected SessionContext sctx;

    private static final Logger LOG = Logger.getLogger(AppointmentEndpoint.class.getName());
    
    public void createAppointment(AppointmentDTO appointment) throws AppBaseException {
 
        List<Examination> examinations = examinationFacade.findAll();
        List<Doctor> doctors = accountFacade.getAllDoctors();
        Examination selectedExamination = null;
        Doctor selectedDoctor = null;
        List<Room> rooms = roomFacade.findAll();
        
        for (Examination examination : examinations) {
            if (examination.getName().equals(appointment.getExamination())) {
                selectedExamination = examination;
                break;
            }
        }
        if (selectedExamination == null) {
            throw new IllegalArgumentException("Błędna nazwa badania"); //TODO: w tym miejscu wymagane zgłoszenie wyjątku aplikacyjnego
        }

        if (appointment.getDoctor() == null){
            String accountType;
            accountType = accountFacade.findLogin(sctx.getCallerPrincipal().getName()).getType();
            if (accountType.equals("Doctor")){
                selectedDoctor = (Doctor)accountFacade.findLogin(sctx.getCallerPrincipal().getName());
            }
        }
          
        for (Doctor d : doctors) {
            if (d.getSecondName().equals(appointment.getDoctor())) {
                selectedDoctor = d;
                break;
            } 
        }
        
        if (selectedDoctor == null) {
            throw new IllegalArgumentException("Błędna nazwa lekarza");
        }

        Room selectedRoom = null;
        for (Room r : rooms) {
            if (r.getRoomNumber().equals(appointment.getRoomNumber())) {
                selectedRoom = r;
                break;
            }
        }
        if (selectedDoctor == null) {
            throw new IllegalArgumentException("Błędna nazwa pokoju"); //TODO: w tym miejscu wymagane zgłoszenie wyjątku aplikacyjnego
        }

        long totalDurationMillisecond = appointment.getTimeEnd().getTime() - appointment.getTimeStart().getTime();
        long totalDurationMinutes = TimeUnit.MILLISECONDS.toMinutes(appointment.getTimeEnd().getTime() - appointment.getTimeStart().getTime());
        double timeSlotAmount = (double)totalDurationMinutes / selectedExamination.getDuration();
        long examinationSlotMillis = totalDurationMillisecond / (long)timeSlotAmount;

        try{
            if (timeSlotAmount % 1 != 0) {
                throw AppointmentException.createWithInvalidTimeSlots();
            }           
        }catch(AppointmentException ae){
            ContextUtils.emitInternationalizedMessage(null, AppointmentException.KEY_APPOINTMENT_INVALID_TIMESLOT);
            return;
        }

        long timeStartHolder = appointment.getTimeStart().getTime();

        try {
            
            for (int i = 0; i < timeSlotAmount; i++) {
                long timeEndHolder = 0;
                Appointment appointmentEntity = new Appointment();
                appointmentEntity.setExaminationId(selectedExamination);
                appointmentEntity.setDoctorId(selectedDoctor);
                appointmentEntity.setRoomId(selectedRoom);
                appointmentEntity.setDate(appointment.getDate());
                appointmentEntity.setTimeStart(new Date(timeStartHolder));
                timeEndHolder = timeStartHolder + examinationSlotMillis;
                appointmentEntity.setTimeEnd(new Date(timeEndHolder));
                appointmentFacade.create(appointmentEntity);
                LOG.log(Level.INFO, appointmentEntity.toString());
                timeStartHolder = timeEndHolder;
            }
            ContextUtils.emitSuccessMessage("appointment");
        }catch(AppointmentException ae){
            LOG.log(Level.INFO, ae.getCause().toString());
            ContextUtils.emitInternationalizedMessage(null, AppointmentException.KEY_DB_CONSTRAINT);
        }
    }

    public List<Appointment> getAllAppointments() {
        return appointmentFacade.findAll();
    }

    public Appointment getAppointmentToEdit(Appointment appointment) {
        Appointment appointmentEntity = appointmentFacade.find(appointment.getId());
//        kontoFacade.refresh(encja);
        return appointmentEntity;
    }

    public void saveEditedAppointment(Appointment a) {
        appointmentFacade.edit(a);
    }

    public String deleteAppointment(Appointment appointment) {
        appointmentFacade.remove(appointment);
        return "deleteAppointmentSuccess";
    }

    public List<Appointment> matchAppointments(Examination examination) {
        return appointmentFacade.matchAppointments(examination);
    }

    public Appointment getUpdatingAppointment(Appointment appointment) {
        return appointmentFacade.find(appointment.getId());
    }

    public void updateAppointment(Appointment updatingAppointment) throws AppBaseException {
        appointmentFacade.edit(updatingAppointment);
            
        
    }

}
