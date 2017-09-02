package pl.lodz.p.it.spjava.medcenter.web.category;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.endpoint.CategoryEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Category;

@Named(value = "editCategoryManageBean")
@RequestScoped
public class EditCategoryPageBean {

    public EditCategoryPageBean() {
    }

    @EJB
    private CategoryEndpoint categoryEndpoint;

    @Inject
    private CategorySession categorySession;

    public String getCategoryToEdit(Category category) {
        categorySession.getCategoryToEdit(category);
        return "editCategory";
    }

    public String saveEditedCategory() {
        categorySession.saveEditedCategory();
        return "editSuccess";
    }

    public Category getEditingCategory() {
        return categorySession.getEditingCategory();
    }


}
