package pl.lodz.p.it.spjava.medcenter.dto;

import java.util.Date;

/**
 *
 * @author pawkos
 */
public class AppointmentDTO {

      
    private String category;
    private String examination;
    private String doctor;
    private String patient;
    private Date date;
    private Date timeStart;
    private Date timeEnd;
    private String roomNumber;

    public AppointmentDTO() {
    }

    public AppointmentDTO(String category, String examination, String doctor, String patient, Date date, Date timeStart, Date timeEnd, String roomNumber) {
        this.category = category;
        this.examination = examination;
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.roomNumber = roomNumber;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" + "category=" + category + ", examination=" + examination + ", doctor=" + doctor + ", patient=" + patient + ", date=" + date + ", timeStart=" + timeStart + ", timeEnd=" + timeEnd + ", roomNumber=" + roomNumber + '}';
    }


    
    




}