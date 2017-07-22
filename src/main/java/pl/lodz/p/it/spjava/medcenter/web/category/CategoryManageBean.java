package pl.lodz.p.it.spjava.medcenter.web.category;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.Column;
import pl.lodz.p.it.spjava.medcenter.dto.CategoryDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.CategoryEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Category;
import pl.lodz.p.it.spjava.medcenter.model.Examination;

@Named(value = "categoryManageBean")
@RequestScoped
public class CategoryManageBean {

    public CategoryManageBean() {
        categoryDto = new CategoryDTO();
    }

    @EJB
    private CategoryEndpoint categoryEndpoint;
    
    @Inject
    private CategorySession categorySession;

    private CategoryDTO categoryDto;

    public Category getEditingCategory() {
        return categorySession.getEditingCategory();
    }
    
    public String getCategoryToEdit(Category category) {
        categorySession.getCategoryToEdit(category);
        return "editCategory";
    }
    
    public String saveEditedCategory() {
        categorySession.saveEditedCategory();
        return "editSuccess";
    }

    public CategoryDTO getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDTO categoryDto) {
        this.categoryDto = categoryDto;
    }

    public String createCategory() {
        categoryEndpoint.createCategory(categoryDto);
        return "success";
    }

}
