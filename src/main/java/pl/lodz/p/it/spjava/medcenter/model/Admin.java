package pl.lodz.p.it.spjava.medcenter.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "PERSONAL_DATA")

public class Admin extends Account implements Serializable {

    public Admin() {
    }  
}
