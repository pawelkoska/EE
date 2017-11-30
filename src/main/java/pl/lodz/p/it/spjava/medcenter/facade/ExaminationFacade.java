/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.it.spjava.medcenter.facade;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.exceptions.DatabaseException;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.exception.ExaminationException;
import pl.lodz.p.it.spjava.medcenter.exception.GeneralOptimisticLockException;
import pl.lodz.p.it.spjava.medcenter.interceptor.LoggingInterceptor;
import pl.lodz.p.it.spjava.medcenter.model.Category;
import pl.lodz.p.it.spjava.medcenter.model.Examination;

/**
 *
 * @author pawkos
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
@RolesAllowed("Admin")
public class ExaminationFacade extends AbstractFacade<Examination> {

    @PersistenceContext(unitName = "pl.lodz.p.it.spjava_MedCenter_war_1.0")
    private EntityManager em;
    private static final Logger LOG = Logger.getLogger(ExaminationFacade.class.getName());
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(Examination entity) throws AppBaseException {
        try {
            super.create(entity);
            em.flush();
        } catch (PersistenceException ex) {
            if (ex.getCause() instanceof DatabaseException && ex.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw ExaminationException.createWithDbCheckConstraintKey(entity, ex);
            } else {
                throw ex;
            }
        }
    }
    
    @Override
    public void edit(Examination entity) throws AppBaseException {
        try {
            super.edit(entity);
            em.flush();
        } catch (OptimisticLockException oe) {
            throw GeneralOptimisticLockException.createWithOptimisticLockKey(oe);
        }
    }
    
    public ExaminationFacade() {
        super(Examination.class);
    }

    public List<Examination> getExaminationsByCategory(Category category) {
        TypedQuery<Examination> tq = em.createNamedQuery("Examination.findByCategory", Examination.class);
        tq.setParameter("id", category);
        return tq.getResultList();
    }

}
