package pl.lodz.p.it.spjava.medcenter.web.examination;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.dto.ExaminationDTO;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.model.Examination;

@Named(value = "editExaminationManageBean")
@RequestScoped
public class EditExaminationPageBean {

    public EditExaminationPageBean() {
        examiantionDto = new ExaminationDTO();
    }

    @Inject
    private ExaminationSession examinationSession;

    private ExaminationDTO examiantionDto;

    public ExaminationDTO getExamiantionDto() {
        return examiantionDto;
    }

    public void setExamiantionDto(ExaminationDTO examiantionDto) {
        this.examiantionDto = examiantionDto;
    }

    public String getExaminationToEdit(Examination examination) {
        examinationSession.getExaminationToEdit(examination);
        return "editExamination";
    }

    public void saveEditedExamination()throws AppBaseException {
        examinationSession.saveEditedExamination();
    }

    public Examination getEditingExamination() {
        return examinationSession.getEditingExamination();
    }

}
