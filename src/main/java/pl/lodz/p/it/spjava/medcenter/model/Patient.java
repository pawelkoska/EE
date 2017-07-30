package pl.lodz.p.it.spjava.medcenter.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PATIENT_DATA")
//@DiscriminatorValue("PATIENT")
//@NamedQueries({
//    @NamedQuery(name = "Pracownik.findAll", query = "SELECT d FROM Pracownik d"),
//    @NamedQuery(name = "Pracownik.findByIntercom", query = "SELECT d FROM Pracownik d WHERE d.intercom = :intercom")
//})
public class Patient extends Account implements Serializable {

    public Patient() {
    }
    
    
}
