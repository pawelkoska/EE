package pl.lodz.p.it.spjava.medcenter.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PERSONAL_DATA")
//@NamedQueries({
//    @NamedQuery(name = "Administrator.findAll", query = "SELECT d FROM Administrator d"),
//    @NamedQuery(name = "Administrator.findByAlarmNumber", query = "SELECT d FROM Administrator d WHERE d.alarmNumber = :alarmNumber")
//})
public class Admin extends Account implements Serializable {

    public Admin() {
    }  
}
