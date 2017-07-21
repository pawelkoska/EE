package pl.lodz.p.it.spjava.medcenter.endpoint;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
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
public class ExaminationEndpoint {

    @EJB
    private ExaminationFacade examinationFacade;

    @EJB
    private CategoryFacade categoryFacade;

    public void createExamination(ExaminationDTO examination) {

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
        examinationEntity.setExaminationCategory(selectedCategory);
        examinationFacade.create(examinationEntity);
    }

}
