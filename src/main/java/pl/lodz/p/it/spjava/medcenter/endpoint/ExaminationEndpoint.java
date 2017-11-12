package pl.lodz.p.it.spjava.medcenter.endpoint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.lodz.p.it.spjava.medcenter.dto.ExaminationDTO;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.exception.CategoryException;
import pl.lodz.p.it.spjava.medcenter.exception.ExaminationException;
import pl.lodz.p.it.spjava.medcenter.facade.CategoryFacade;
import pl.lodz.p.it.spjava.medcenter.facade.ExaminationFacade;
import pl.lodz.p.it.spjava.medcenter.interceptor.LoggingInterceptor;
import pl.lodz.p.it.spjava.medcenter.model.Category;
import pl.lodz.p.it.spjava.medcenter.model.Examination;
import pl.lodz.p.it.spjava.medcenter.model.utils.ContextUtils;

/**
 *
 * @author pawkos
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class ExaminationEndpoint {

    @EJB
    private ExaminationFacade examinationFacade;

    @EJB
    private CategoryFacade categoryFacade;

    public void createExamination(ExaminationDTO examination) throws ParseException, AppBaseException {
        List<Category> categories = categoryFacade.findAll();
        Category selectedCategory = null;
        for (Category category : categories) {
            if (category.getName().equals(examination.getExaminationCategory())) {
                selectedCategory = category;
                break;
            }
        }
        if (selectedCategory == null) {
            throw new IllegalArgumentException("Błędna nazwa kategorii"); //TODO: w tym miejscu wymagane zgłoszenie wyjątku aplikacyjnego
        }

        Examination examinationEntity = new Examination();
        examinationEntity.setName(examination.getName());
        examinationEntity.setExaminationDescription(examination.getExaminationDescription());
        examinationEntity.setCategoryId(selectedCategory);        
        examinationEntity.setDuration(examination.getExaminationDuration());
        try{
            examinationFacade.create(examinationEntity);  
            ContextUtils.emitSuccessMessage("examinationList");
        } catch(ExaminationException ee) {
            ContextUtils.emitInternationalizedMessage(null, ExaminationException.KEY_DB_CONSTRAINT);
        }
    }

    public List<Examination> getExaminationsByCategory(Category category) {
        return examinationFacade.getExaminationsByCategory(category);
    }

    public List<Examination> getAllExaminations() {
        return examinationFacade.findAll();
    }

    public void deleteExamination(Examination examination) {
        examinationFacade.remove(examination);
    }

    public Examination getExaminationToEdit(Examination examination) {
        Examination examinationEntity = examinationFacade.find(examination.getId());
//        kontoFacade.refresh(encja);
        return examinationEntity;
    }

    public void saveEditedExamination(Examination e) {            
        List<Category> categories = categoryFacade.findAll();
        for (Category category : categories) {
            if (category.getName().equals(e.getCategoryId().getName())) {
                e.setCategoryId(category);
            }
        }
        examinationFacade.edit(e);
    }
}
