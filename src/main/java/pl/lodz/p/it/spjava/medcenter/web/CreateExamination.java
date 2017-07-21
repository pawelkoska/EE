package pl.lodz.p.it.spjava.medcenter.web.category;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import pl.lodz.p.it.spjava.medcenter.dto.CategoryDTO;
import pl.lodz.p.it.spjava.medcenter.dto.ExaminationDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.CategoryEndpoint;
import pl.lodz.p.it.spjava.medcenter.endpoint.ExaminationEndpoint;


@Named(value = "createExamination")
@RequestScoped
public class CreateExamination {

    @EJB
    private ExaminationEndpoint examinationEndpoint;

    private ExaminationDTO examiantionDto;

    public ExaminationDTO getExaminationDto() {
        return examiantionDto;
    }

    public void setExaminationDto(ExaminationDTO examiantionDto) {
        this.examiantionDto = examiantionDto;
    }

    public CreateExamination() {
        examiantionDto = new ExaminationDTO();
    }

    public String createExamination() {
        examinationEndpoint.createExamination(examiantionDto);
        return "examinationSuccess";
    }
}
