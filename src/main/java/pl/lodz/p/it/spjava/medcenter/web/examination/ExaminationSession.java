package pl.lodz.p.it.spjava.medcenter.web.examination;

import pl.lodz.p.it.spjava.medcenter.web.category.*;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import pl.lodz.p.it.spjava.medcenter.endpoint.ExaminationEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Examination;

@Named(value = "examinationSession")
@SessionScoped
public class ExaminationSession implements Serializable{

    @EJB
    private ExaminationEndpoint examinationEndpoint;

    private Examination editingExamination;

    public Examination getEditingExamination() {
        return editingExamination;
    }

    public void saveEditedExamination() {
        if (null == editingExamination) {
            throw new IllegalArgumentException("Proba edytacji z pominieciem formularza");
        }
        examinationEndpoint.saveEditedExamination(editingExamination);
        editingExamination = null;
    }
    
    public void getExaminationToEdit(Examination examination) {
        editingExamination = examinationEndpoint.getExaminationToEdit(examination);
    }

    
}
