package pl.lodz.p.it.spjava.medcenter.web.examination;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    
    private static final Logger LOG = Logger.getLogger(CreateExamiantionPageBean.class.getName());

    
    
    private List<Examination> examinationObjList = new ArrayList<>();

    private ExaminationDTO examinationDto = new ExaminationDTO();

    private List<Integer> durationList = new ArrayList<>();

    public List<Integer> getDurationList() {
        durationList.add(15);
        durationList.add(30);
        durationList.add(60);
        return durationList;
    }

    public void setDurationList(List<Integer> durationList) {
        this.durationList = durationList;
    }

    public String createExamination() throws ParseException {
        LOG.log(Level.INFO, examinationDto.toString());
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
        for (Examination examination : allExaminations) {
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
