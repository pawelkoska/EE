package pl.lodz.p.it.spjava.medcenter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "PERSONAL_DATA")
//@DiscriminatorValue("PATIENT")
//@NamedQueries({
//    @NamedQuery(name = "Pracownik.findAll", query = "SELECT d FROM Pracownik d"),
//    @NamedQuery(name = "Pracownik.findByIntercom", query = "SELECT d FROM Pracownik d WHERE d.intercom = :intercom")
//})
public class Patient extends Account implements Serializable {

    public Patient() {
    }
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    private List<Appointment> appointmentList = new ArrayList<>();

}
