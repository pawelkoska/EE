package pl.lodz.p.it.spjava.medcenter.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ADMIN_DATA")
@DiscriminatorValue("ADMIN")
//@NamedQueries({
//    @NamedQuery(name = "Administrator.findAll", query = "SELECT d FROM Administrator d"),
//    @NamedQuery(name = "Administrator.findByAlarmNumber", query = "SELECT d FROM Administrator d WHERE d.alarmNumber = :alarmNumber")
//})
public class Admin extends Account implements Serializable {

    public Admin() {
    }
    
    @NotNull
    @Size(max=12,message="{constraint.string.length.toolong}")
    @Column(name = "AlarmNumber", unique=true, nullable=false, length=12)
    private String alarmNumber;

    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String alarmNumber) {
        this.alarmNumber = alarmNumber;
    }
}
