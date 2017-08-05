/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.it.spjava.medcenter.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.lodz.p.it.spjava.medcenter.model.Account;
import pl.lodz.p.it.spjava.medcenter.model.Doctor;
import pl.lodz.p.it.spjava.medcenter.model.Patient;

/**
 *
 * @author pawkos
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> {

    @PersistenceContext(unitName = "pl.lodz.p.it.spjava_MedCenter_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }

    public List<Account> getAllAccounts() {
        TypedQuery<Account> tq = em.createNamedQuery("Account.getAllAccounts", Account.class);
        return tq.getResultList();
    }

    public List<Doctor> getAllDoctors() {
        TypedQuery<Doctor> tq = em.createNamedQuery("Account.getAllDoctors", Doctor.class);
        return tq.getResultList();
    }

    public List<Patient> getAllPatients() {
        TypedQuery<Patient> tq = em.createNamedQuery("Account.getAllPatients", Patient.class);
        return tq.getResultList();
    }
}
