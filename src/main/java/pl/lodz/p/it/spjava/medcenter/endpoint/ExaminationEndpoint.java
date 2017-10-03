package pl.lodz.p.it.spjava.medcenter.endpoint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import pl.lodz.p.it.spjava.medcenter.dto.ExaminationDTO;
import pl.lodz.p.it.spjava.medcenter.facade.CategoryFacade;
import pl.lodz.p.it.spjava.medcenter.facade.ExaminationFacade;
import pl.lodz.p.it.spjava.medcenter.model.Category;
import pl.lodz.p.it.spjava.medcenter.model.Examination;

/**
 *
 * @author pawkos
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ExaminationEndpoint {

    @EJB
    private ExaminationFacade examinationFacade;

    @EJB
    private CategoryFacade categoryFacade;

    public void createExamination(ExaminationDTO examination) throws ParseException {
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
        examinationFacade.create(examinationEntity);
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
