package pl.lodz.p.it.spjava.medcenter.web.examination;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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

    @EJB
    private CategoryEndpoint categoryEndpoint;

    private ExaminationDTO examiantionDto;

    private List<CategoryDTO> categoryList = new ArrayList<>();

    public List<CategoryDTO> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryDTO> categoryList) {
        this.categoryList = categoryList;
    }

    @PostConstruct
    public void prepareCategory() {
        List<CategoryDTO> listCategoryDTO = categoryEndpoint.listAllCategories();
        for (CategoryDTO categoryDTO : listCategoryDTO) {
            categoryList.add(categoryDTO);
        }
    }

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
