package pl.lodz.p.it.spjava.medcenter.web.examination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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

    @EJB
    private CategoryEndpoint categoryEndpoint;

    private ExaminationDTO examiantionDto;

    private List<Category> categoryObjList = new ArrayList<>();
    private List<String> categoryNamesList = new ArrayList<>();

    public List<String> getCategoryNamesList() {
        return categoryNamesList;
    }

    public void setCategoryNamesList(List<String> categoryNamesList) {
        this.categoryNamesList = categoryNamesList;
    }

    public List<Category> getCategoryObjList() {
        return categoryObjList;
    }

    public void setCategoryObjList(List<Category> categoryObjList) {
        this.categoryObjList = categoryObjList;
    }

    @PostConstruct
    public void getAllCategories() {
        List<Category> allCategories = categoryEndpoint.getAllCategories();
        for (Category category : allCategories) {
            categoryObjList.add(category);
            categoryNamesList.add(category.getName());
        }
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
}
