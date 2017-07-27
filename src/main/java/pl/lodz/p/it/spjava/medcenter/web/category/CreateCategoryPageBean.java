package pl.lodz.p.it.spjava.medcenter.web.category;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.endpoint.CategoryEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Category;

@Named(value = "createCategoryPageBean")
@RequestScoped
public class CreateCategoryPageBean {

    public CreateCategoryPageBean() {
    }

    @EJB
    private CategoryEndpoint categoryEndpoint;

    @Inject
    private CategorySession categorySession;

    private List<Category> categoryObjList = new ArrayList<>();
    private List<String> categoryNamesList = new ArrayList<>();
    private Category category = new Category();

    public String createCategory() {
        return categorySession.createCategory(category);
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
