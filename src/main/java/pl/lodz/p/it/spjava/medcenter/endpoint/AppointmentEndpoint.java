package pl.lodz.p.it.spjava.medcenter.endpoint;

import java.util.Date;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import pl.lodz.p.it.spjava.medcenter.dto.AppointmentDTO;
import pl.lodz.p.it.spjava.medcenter.facade.AccountFacade;
import pl.lodz.p.it.spjava.medcenter.facade.AppointmentFacade;
import pl.lodz.p.it.spjava.medcenter.facade.CategoryFacade;
import pl.lodz.p.it.spjava.medcenter.facade.ExaminationFacade;
import pl.lodz.p.it.spjava.medcenter.facade.RoomFacade;
import pl.lodz.p.it.spjava.medcenter.model.Appointment;
import pl.lodz.p.it.spjava.medcenter.model.Doctor;
import pl.lodz.p.it.spjava.medcenter.model.Examination;
import pl.lodz.p.it.spjava.medcenter.model.Room;

/**
 *
 * @author pawkos
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
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

    public void createAppointment(AppointmentDTO appointment) {
        List<Examination> examinations = examinationFacade.findAll();
        Examination selectedExamination = null;
        for (Examination examination : examinations) {
            if (examination.getName().equals(appointment.getExamination())) {
                selectedExamination = examination;
                break;
            }
        }
        if (selectedExamination == null) {
            throw new IllegalArgumentException("Błędna nazwa badania"); //TODO: w tym miejscu wymagane zgłoszenie wyjątku aplikacyjnego
        }

        List<Doctor> doctors = accountFacade.getAllDoctors();
        Doctor selectedDoctor = null;
        for (Doctor d : doctors) {
            if (d.getSecondName().equals(appointment.getDoctor())) {
                selectedDoctor = d;
                break;
            }
        }
        if (selectedDoctor == null) {
            throw new IllegalArgumentException("Błędna nazwa doktora"); //TODO: w tym miejscu wymagane zgłoszenie wyjątku aplikacyjnego
        }

        List<Room> rooms = roomFacade.findAll();
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

        //DEBUGING
        long totalDurationMillisecond = appointment.getTimeEnd().getTime() - appointment.getTimeStart().getTime();
        long totalDurationMinutes = TimeUnit.MILLISECONDS.toMinutes(appointment.getTimeEnd().getTime() - appointment.getTimeStart().getTime());
        double timeSlotAmount = (double)totalDurationMinutes / selectedExamination.getDuration();
        long examinationSlotMillis = totalDurationMillisecond / (long)timeSlotAmount;
        LOG.log(Level.INFO, "Total duration minutes: " + Long.toString(totalDurationMinutes) + "mins.");
        LOG.log(Level.INFO, "Total duration milliseconds: " + totalDurationMillisecond);
        LOG.log(Level.INFO, "Amount of time slots/examinations: " + timeSlotAmount);

        if (timeSlotAmount % 1 != 0) {
            throw new IllegalArgumentException("Nie mozna wyznaczyc wizyt z podanego przedzialu czasowego");
        }

        long timeStartHolder = appointment.getTimeStart().getTime();

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
            timeStartHolder = timeEndHolder;
        }
    }
    private static final Logger LOG = Logger.getLogger(AppointmentEndpoint.class.getName());

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

    public void updateAppointment(Appointment updatingAppointment) {
        appointmentFacade.edit(updatingAppointment);
    }

}
