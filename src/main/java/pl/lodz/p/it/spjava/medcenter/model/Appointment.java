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
import javax.validation.constraints.NotNull;
import static pl.lodz.p.it.spjava.medcenter.model.Account_.name;

/**
 *
 * @author pawkos
 */
@Entity
@Table(name = "APPOINTMENT")
public class Appointment extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private Category categoryId;

    @JoinColumn(name = "EXAMINATION_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private Examination examinationId;

    @Temporal(TemporalType.DATE)
    @Column(name = "appointment_date")
    @NotNull
    private Date appointmentDate;

    @Temporal(TemporalType.TIME)
    @Column(name = "appointment_time")
    @NotNull
    private Date appointmentTime;

    @JoinColumn(name = "DOCTOR", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private Doctor doctorId;

    @JoinColumn(name = "PATIENT", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private Patient patientId;

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
        return "medcenter.model.Category[ id=" + id + " ]";
    }

    @Override
    protected Object getBusinessKey() {
        return name;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }



    public Examination getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Examination examinationId) {
        this.examinationId = examinationId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

}
