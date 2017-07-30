package pl.lodz.p.it.spjava.medcenter.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "DOCTOR_DATA")
//@DiscriminatorValue("DOCTOR")
//@NamedQueries({
//    @NamedQuery(name = "Pracownik.findAll", query = "SELECT d FROM Pracownik d"),
//    @NamedQuery(name = "Pracownik.findByIntercom", query = "SELECT d FROM Pracownik d WHERE d.intercom = :intercom")
//})
public class Doctor extends Account implements Serializable {

    public Doctor() {
    }

    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID",  nullable = false)
    @ManyToOne
    private Category specialization;

    public Category getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Category specialization) {
        this.specialization = specialization;
    }

}
