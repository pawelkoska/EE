/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.it.spjava.medcenter.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
     
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID",  nullable = false)
    @ManyToOne
    private Category categoryId;
    
    @JoinColumn(name = "EXAMINATION_ID", referencedColumnName = "ID",  nullable = false)
    @ManyToOne
    private Examination examinationId;

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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

}
