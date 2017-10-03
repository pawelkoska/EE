/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.it.spjava.medcenter.facade;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import pl.lodz.p.it.spjava.medcenter.dto.AppointmentDTO;
import pl.lodz.p.it.spjava.medcenter.model.Appointment;
import pl.lodz.p.it.spjava.medcenter.model.Appointment_;
import pl.lodz.p.it.spjava.medcenter.model.Doctor;
import pl.lodz.p.it.spjava.medcenter.model.Examination;
import pl.lodz.p.it.spjava.medcenter.model.Examination_;

/**
 *
 * @author pawkos
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AppointmentFacade extends AbstractFacade<Appointment> {

    private static final Logger LOG = Logger.getLogger(AppointmentFacade.class.getName());

    
    
    @PersistenceContext(unitName = "pl.lodz.p.it.spjava_MedCenter_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppointmentFacade() {
        super(Appointment.class);
    }

    public List<Appointment> matchAppointments(Examination examination) {
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Appointment> query = cb.createQuery(Appointment.class);
        Root<Appointment> from = query.from(Appointment.class);
        query = query.select(from);
        Predicate criteria = cb.conjunction();
        
        if (null != examination) {
            criteria = cb.and(criteria, cb.equal(from.get(Appointment_.examinationId), examination));
        }
//        if (null != appointmentDto.getDoctor()&& !(appointmentDto.getDoctor().isEmpty())) {
//            criteria = cb.and(criteria, cb.like(from.<String>get(Appointment_.doctorId.getName()), appointmentDto.getDoctor()));
//        }
//        if (appointmentDto.getDate() != null) {
//            criteria = cb.and(criteria, cb.greaterThan(from.get(Appointment_.date), appointmentDto.getDate()));
//        }
        
        query = query.where(criteria);
        TypedQuery<Appointment> tq = em.createQuery(query);
        return tq.getResultList();
    }

}
