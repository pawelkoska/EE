package pl.lodz.p.it.spjava.medcenter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "DOCTOR_DATA")

public class Doctor extends Account implements Serializable {

    public Doctor() {
    }

    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private Category specialization;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctorId")
    private List<Appointment> appointmentList = new ArrayList<>();

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public Category getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Category specialization) {
        this.specialization = specialization;
    }

}
