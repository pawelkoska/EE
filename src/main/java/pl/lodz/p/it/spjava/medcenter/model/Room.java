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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pawkos
 */
@Entity
@Table(name = "ROOM", uniqueConstraints = @UniqueConstraint(columnNames = {"ROOM_NUMBER", "EXAMINATION_ID"}))
public class Room extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ROOM_NUMBER", nullable = false)
    @NotNull(message = "Room number can not be empty")
    private String roomNumber;

    @ManyToOne
    @JoinColumn(name = "EXAMINATION_ID", referencedColumnName = "ID", nullable = false)
    @NotNull(message = "Examination type is required")
    private Examination examinationType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private List<Appointment> appointmentList = new ArrayList<>();

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Examination getExaminationType() {
        return examinationType;
    }

    public void setExaminationType(Examination examinationType) {
        this.examinationType = examinationType;
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
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "medcenter.model.Room[ id=" + id + " ]";
    }

    @Override
    protected Object getBusinessKey() {
        return roomNumber;
    }

    @Override
    public Long getId() {
        return id;
    }

}
