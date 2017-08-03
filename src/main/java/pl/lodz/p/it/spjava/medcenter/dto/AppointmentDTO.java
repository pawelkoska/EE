package pl.lodz.p.it.spjava.medcenter.dto;

/**
 *
 * @author pawkos
 */
public class AppointmentDTO {

      
    private String category;
    private String examination;
    private String doctor;
    private String patient;
    private String date;
    private String time;

    public AppointmentDTO() {
    }
    
    public AppointmentDTO(String category, String examination, String doctor, String patient, String date, String time) {
        this.category = category;
        this.examination = examination;
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    




}