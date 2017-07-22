package pl.lodz.p.it.spjava.medcenter.web.category;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import pl.lodz.p.it.spjava.medcenter.endpoint.CategoryEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Category;

@Named(value = "categorySession")
@SessionScoped
public class CategorySession implements Serializable{

    @EJB
    private CategoryEndpoint categoryEndpoint;

    private Category editingCategory;

    public Category getEditingCategory() {
        return editingCategory;
    }

    public void saveEditedCategory() {
        if (null == editingCategory) {
            throw new IllegalArgumentException("Proba edytacji z pominieciem formularza");
        }
        categoryEndpoint.saveEditedCategory(editingCategory);
        editingCategory = null;
    }
    
    public void getCategoryToEdit(Category category) {
        editingCategory = categoryEndpoint.getCategoryToEdit(category);
    }

    
}
