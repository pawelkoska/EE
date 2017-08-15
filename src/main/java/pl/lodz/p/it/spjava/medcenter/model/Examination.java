/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.it.spjava.medcenter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author pawkos
 */
@Entity
@Table(name = "EXAMINATION")
@NamedQueries({
    @NamedQuery(name = "Examination.findByCategory", query = "SELECT e FROM Examination e WHERE e.categoryId = :id"),})
public class Examination extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @Column(name = "DESCRIPTION")
    @NotNull
    @Size(min = 10, max = 300)
    private String examinationDescription;

    @Column(name = "DURATION", nullable = false)
    private int duration;

    
    
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private Category categoryId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examinationType")
    private List<Room> roomList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examinationId")
    private List<Appointment> appointmentList = new ArrayList<>();

    
    
    
    
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category category) {
        this.categoryId = category;
    }

    public int getDuration() {
        return duration;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExaminationDescription() {
        return examinationDescription;
    }

    public void setExaminationDescription(String examinationDescription) {
        this.examinationDescription = examinationDescription;
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
        if (!(object instanceof Examination)) {
            return false;
        }
        Examination other = (Examination) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "medcenter.model.Examination[ id=" + id + " ]";
    }

    @Override
    protected Object getBusinessKey() {
        return name;
    }

}
