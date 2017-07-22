package pl.lodz.p.it.spjava.medcenter.web.category;

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
import pl.lodz.p.it.spjava.medcenter.model.Category;


@Named(value = "createExamination")
@RequestScoped
public class CreateExamination {

    @EJB
    private ExaminationEndpoint examinationEndpoint;
    
    @EJB
    private CategoryEndpoint categoryEndpoint;

    private ExaminationDTO examiantionDto;
    
    private List<Category> categoryObj = new ArrayList<>();
    
    private List<String> categoryList = new ArrayList<>();

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Category> getCategoryObj() {
        return categoryObj;
    }

    public void setCategoryObj(List<Category> categoryObj) {
        this.categoryObj = categoryObj;
    }
    
    @PostConstruct
    public void getAllCategories(){
        List<Category> allCategories = categoryEndpoint.getAllCategories();
        for (Category category : allCategories) {
            categoryObj.add(category);
            categoryList.add(category.getName());
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
