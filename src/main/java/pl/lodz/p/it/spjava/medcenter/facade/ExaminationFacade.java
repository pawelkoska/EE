/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.it.spjava.medcenter.facade;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.lodz.p.it.spjava.medcenter.model.Category;
import pl.lodz.p.it.spjava.medcenter.model.Examination;

/**
 *
 * @author pawkos
 */
@Stateless
public class ExaminationFacade extends AbstractFacade<Examination> {

    @PersistenceContext(unitName = "pl.lodz.p.it.spjava_MedCenter_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    private static final Logger LOG = Logger.getLogger(ExaminationFacade.class.getName());
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
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
