package pl.lodz.p.it.spjava.medcenter.web.examination;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import pl.lodz.p.it.spjava.medcenter.dto.ExaminationDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.ExaminationEndpoint;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.model.Category;
import pl.lodz.p.it.spjava.medcenter.model.Examination;

@Named(value = "examinationSession")
@SessionScoped
public class ExaminationSession implements Serializable {

    @EJB
    private ExaminationEndpoint examinationEndpoint;

    private Examination editingExamination;
    private Examination deletedExamination;

    public Examination getDeletedExamination() {
        return deletedExamination;
    }

    public void createExamination(ExaminationDTO examination) throws ParseException, AppBaseException{
        examinationEndpoint.createExamination(examination);
    }

    public List<Examination> getAllExaminations() {
        return examinationEndpoint.getAllExaminations();
    }

    public Examination getEditingExamination() {
        return editingExamination;
    }

    public void saveEditedExamination() throws AppBaseException{
        if (null == editingExamination) {
            throw new IllegalArgumentException("Proba edytacji z pominieciem formularza");
        }
        examinationEndpoint.saveEditedExamination(editingExamination);
        editingExamination = null;
    }

    public void getExaminationToEdit(Examination examination) {
        editingExamination = examinationEndpoint.getExaminationToEdit(examination);
    }

    public List<Examination> getExaminationsByCategory(Category category) {
        return examinationEndpoint.getExaminationsByCategory(category);
    }
}
