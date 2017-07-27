package pl.lodz.p.it.spjava.medcenter.web.category;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.Column;
import pl.lodz.p.it.spjava.medcenter.endpoint.CategoryEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Category;
import pl.lodz.p.it.spjava.medcenter.model.Examination;

@Named(value = "editCategoryManageBean")
@RequestScoped
public class EditCategoryPageBean {

    public EditCategoryPageBean() {
    }

    @EJB
    private CategoryEndpoint categoryEndpoint;

    @Inject
    private CategorySession categorySession;

    private List<Category> categoryObjList = new ArrayList<>();
    private List<String> categoryNamesList = new ArrayList<>();

    public List<String> getCategoryNamesList() {
        return categoryNamesList;
    }

    public String getCategoryToEdit(Category category) {
        categorySession.getCategoryToEdit(category);
        return "editCategory";
    }

    public String saveEditedCategory() {
        categorySession.saveEditedCategory();
        return "editSuccess";
    }

    public List<Category> getCategoryObjList() {
        return categoryObjList;
    }

    public Category getEditingCategory() {
        return categorySession.getEditingCategory();
    }

    @PostConstruct
    public void getAllCategories() {
        List<Category> allCategories = categoryEndpoint.getAllCategories();
        for (Category category : allCategories) {
            categoryObjList.add(category);
            categoryNamesList.add(category.getName());
        }
    }

}
