package pl.lodz.p.it.spjava.medcenter.endpoint;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import pl.lodz.p.it.spjava.medcenter.facade.CategoryFacade;
import pl.lodz.p.it.spjava.medcenter.model.Category;

/**
 *
 * @author pawkos
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CategoryEndpoint {

    @EJB
    private CategoryFacade categoryFacade;

    public void createCategory(Category category) {
        categoryFacade.create(category);
    }

    public List<Category> getAllCategories() {
        return categoryFacade.findAll();
    }

    public Category getCategoryToEdit(Category category) {
        Category categoryEntity = categoryFacade.find(category.getId());
//        kontoFacade.refresh(encja);
        return categoryEntity;
    }

    public void saveEditedCategory(Category c) {
        categoryFacade.edit(c);
    }

    public String deleteCategory(Category category) {
        categoryFacade.remove(category);
        return "deleteCategorySuccess";
    }

}
