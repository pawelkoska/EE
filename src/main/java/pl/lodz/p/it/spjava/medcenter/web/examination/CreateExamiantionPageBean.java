package pl.lodz.p.it.spjava.medcenter.web.examination;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.dto.ExaminationDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.ExaminationEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Examination;

@Named(value = "createExaminationPageBean")
@RequestScoped
public class CreateExamiantionPageBean {

    public CreateExamiantionPageBean() {
    }

    @EJB
    private ExaminationEndpoint examinationEndpoint;

    @Inject
    private ExaminationSession examinationSession;

    private List<Examination> examinationObjList = new ArrayList<>();
    
    private ExaminationDTO examinationDto = new ExaminationDTO();

    public String createExamination() {
        return examinationSession.createExamination(examinationDto);
    }

    public List<Examination> getExaminationObjList() {
        return examinationObjList;
    }

    public void setExaminationObjList(List<Examination> examinationObjList) {
        this.examinationObjList = examinationObjList;
    }

    @PostConstruct
    public void getAllExaminations() {
        List<Examination> allExaminations = examinationEndpoint.getAllExaminations();
        for (Examination examination: allExaminations) {
            examinationObjList.add(examination);
        }
    }

    public ExaminationDTO getExaminationDto() {
        return examinationDto;
    }

    public void setExaminationDto(Examination examination) {
        this.examinationDto = examinationDto;
    }

}
