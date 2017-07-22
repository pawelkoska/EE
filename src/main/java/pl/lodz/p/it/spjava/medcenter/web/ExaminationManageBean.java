package pl.lodz.p.it.spjava.medcenter.web.category;

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

@Named(value = "ExaminationManageBean")
@RequestScoped
public class ExaminationManageBean {

    @EJB
    private ExaminationEndpoint examinationEndpoint;
    @EJB
    private ExaminationFacade examinationFacade;

    @EJB
    private CategoryEndpoint categoryEndpoint;

    private ExaminationDTO examiantionDto;

    private List<Category> categoryObjList = new ArrayList<>();

    private List<String> categoryList = new ArrayList<>();

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
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
            categoryList.add(category.getName());
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

    public ExaminationManageBean() {
        examiantionDto = new ExaminationDTO();
    }

    public String createExamination() {
        examinationEndpoint.createExamination(examiantionDto);
        return "examinationSuccess";
    }
}
