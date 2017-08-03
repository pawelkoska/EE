package pl.lodz.p.it.spjava.medcenter.endpoint;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import pl.lodz.p.it.spjava.medcenter.dto.AppointmentDTO;
import pl.lodz.p.it.spjava.medcenter.facade.AccountFacade;
import pl.lodz.p.it.spjava.medcenter.facade.AppointmentFacade;
import pl.lodz.p.it.spjava.medcenter.facade.CategoryFacade;
import pl.lodz.p.it.spjava.medcenter.facade.ExaminationFacade;
import pl.lodz.p.it.spjava.medcenter.model.Appointment;
import pl.lodz.p.it.spjava.medcenter.model.Category;
import pl.lodz.p.it.spjava.medcenter.model.Doctor;
import pl.lodz.p.it.spjava.medcenter.model.Examination;
import pl.lodz.p.it.spjava.medcenter.model.Patient;

/**
 *
 * @author pawkos
 */
@Stateful
public class AppointmentEndpoint {

    @EJB
    private AppointmentFacade appointmentFacade;
    
    @EJB
    private CategoryFacade categoryFacade;
    
    @EJB
    private AccountFacade accountFacade;
    
    @EJB
    private ExaminationFacade examinationFacade;

    public void createAppointment(AppointmentDTO appointment) throws ParseException {
        
        List<Category> categories = categoryFacade.findAll();
        Category selectedCategory = null;
        for (Category category : categories) {
            if (category.getName().equals(appointment.getCategory())) {
                selectedCategory = category;
                break;
            }
        }
        if (selectedCategory == null) {
            throw new IllegalArgumentException("Błędna nazwa kategorii"); //TODO: w tym miejscu wymagane zgłoszenie wyjątku aplikacyjnego
        }
        
        List<Examination> examinations = examinationFacade.findAll();
        Examination selectedExamination = null;
        for (Examination examination : examinations) {
            if (examination.getName().equals(appointment.getExamination())) {
                selectedExamination = examination;
                break;
            }
        }
        if (selectedCategory == null) {
            throw new IllegalArgumentException("Błędna nazwa badania"); //TODO: w tym miejscu wymagane zgłoszenie wyjątku aplikacyjnego
        }
        
        List<Doctor> doctors = accountFacade.getAllDoctors();
        Doctor selectedDoctor = null;
        for (Doctor d : doctors) {
            if (d.getName().equals(appointment.getExamination())) {
                selectedDoctor = d;
                break;
            }
        }
        if (selectedCategory == null) {
            throw new IllegalArgumentException("Błędna nazwa doktora"); //TODO: w tym miejscu wymagane zgłoszenie wyjątku aplikacyjnego
        }
        
        List<Patient> patients = accountFacade.getAllPatients();
        Patient selectedPatient = null;
        for (Patient p : patients) {
            if (p.getName().equals(appointment.getPatient())) {
                selectedPatient = p;
                break;
            }
        }
        if (selectedCategory == null) {
            throw new IllegalArgumentException("Błędna nazwa pacjenta"); //TODO: w tym miejscu wymagane zgłoszenie wyjątku aplikacyjnego
        }

        Appointment appointmentEntity = new Appointment();
        appointmentEntity.setCategoryId(selectedCategory);
        appointmentEntity.setExaminationId(selectedExamination);
        appointmentEntity.setDoctorId(selectedDoctor);
        appointmentEntity.setPatientId(selectedPatient);

        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(appointment.getDate());       
        appointmentEntity.setAppointmentDate(date);
        
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
        Date time = formatTime.parse(appointment.getTime());       
        appointmentEntity.setAppointmentDate(time);   
        
        appointmentFacade.create(appointmentEntity);

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

}
