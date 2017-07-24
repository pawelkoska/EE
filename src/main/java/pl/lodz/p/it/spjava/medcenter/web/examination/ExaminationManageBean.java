package pl.lodz.p.it.spjava.medcenter.web.examination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.dto.CategoryDTO;
import pl.lodz.p.it.spjava.medcenter.dto.ExaminationDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.CategoryEndpoint;
import pl.lodz.p.it.spjava.medcenter.endpoint.ExaminationEndpoint;
import pl.lodz.p.it.spjava.medcenter.facade.ExaminationFacade;
import pl.lodz.p.it.spjava.medcenter.model.Category;
import pl.lodz.p.it.spjava.medcenter.model.Examination;

@Named(value = "examinationManageBean")
@RequestScoped
public class ExaminationManageBean {

    public ExaminationManageBean() {
        examiantionDto = new ExaminationDTO();
    }

    @EJB
    private ExaminationEndpoint examinationEndpoint;

    @Inject
    private ExaminationSession examinationSession;

    private ExaminationDTO examiantionDto;

    private List<Examination> examinationObjList = new ArrayList<>();

    public Examination getEditingExamination() {
        return examinationSession.getEditingExamination();
    }

    public String saveEditedExamination() {
        examinationSession.saveEditedExamination();
        return "editExaminationSuccess";
    }

    public List<Examination> getExaminationObjList() {
        return examinationObjList;
    }

    public void setExaminationObjList(List<Examination> examinationObjList) {
        this.examinationObjList = examinationObjList;
    }

    public List<Examination> getExaminationsByCategory(Category category) {
        return examinationEndpoint.getExaminationsByCategory(category);
    }

    public ExaminationDTO getExaminationDto() {
        return examiantionDto;
    }

    public void setExaminationDto(ExaminationDTO examiantionDto) {
        this.examiantionDto = examiantionDto;
    }

    public String createExamination() {
        examinationEndpoint.createExamination(examiantionDto);
        return "examinationSuccess";
    }

    @PostConstruct
    public void getAllExaminations() {
        List<Examination> allExaminations = examinationEndpoint.getAllExaminations();
        for (Examination examination : allExaminations) {
            examinationObjList.add(examination);
        }
    }

    public String deleteExamination(Examination examination) {
        examinationEndpoint.deleteExamination(examination);
        return "deleteExaminationSuccess";
    }

    public String getCategoryToEdit(Examination examination) {
        examinationSession.getExaminationToEdit(examination);
        return "editExamination";
    }

}
