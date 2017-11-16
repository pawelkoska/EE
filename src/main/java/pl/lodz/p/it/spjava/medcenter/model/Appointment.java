/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.it.spjava.medcenter.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import static pl.lodz.p.it.spjava.medcenter.model.Account_.name;

/**
 *
 * @author pawkos
 */
@Entity
@Table(name = "APPOINTMENT", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ROOM", "APPOINTMENT_DATE", "TIME_START", "TIME_END"}),
    @UniqueConstraint(columnNames = {"DOCTOR", "APPOINTMENT_DATE", "TIME_START", "TIME_END"})
})
public class Appointment extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "APPOINTMENT_DATE", nullable = false)
    @NotNull(message = "Time start of appointment can not be empty")
    private Date date;

    @Temporal(TemporalType.TIME)
    @Column(name = "TIME_START", nullable = false)
    @NotNull(message = "Time start of appointment can not be empty")
    private Date timeStart;

    @Temporal(TemporalType.TIME)
    @Column(name = "TIME_END", nullable = false)
    private Date timeEnd;

    @Column(name = "APPOINTMENT_RESULT", nullable = true)
    @Size(min = 10, max = 1000, message = "Appointment result has to be between 10 and 1000 characters")
    private String appointmentResult;

    @JoinColumn(name = "EXAMINATION_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    public Examination examinationId;

    @JoinColumn(name = "DOCTOR", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private Doctor doctorId;

    @JoinColumn(name = "PATIENT", referencedColumnName = "ID", nullable = true)
    @ManyToOne
    private Patient patientId;

    @JoinColumn(name = "ROOM", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private Room roomId;

    public Examination getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Examination examinationId) {
        this.examinationId = examinationId;
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

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
    }

    public String getAppointmentResult() {
        return appointmentResult;
    }

    public void setAppointmentResult(String appointmentResult) {
        this.appointmentResult = appointmentResult;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Appointment: " + id + roomId + timeStart + timeEnd + date;
    }
    
    

    @Override
    protected Object getBusinessKey() {
        return name;
    }

}
