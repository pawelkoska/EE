package pl.lodz.p.it.spjava.medcenter.endpoint;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import pl.lodz.p.it.spjava.medcenter.dto.CategoryDTO;
import pl.lodz.p.it.spjava.medcenter.facade.CategoryFacade;
import pl.lodz.p.it.spjava.medcenter.model.Category;


/**
 *
 * @author pawkos
 */
@Stateful
public class CategoryEndpoint {

    @EJB
    private CategoryFacade categoryFacade;
    
    public void createCategory(CategoryDTO category) {
        
        Category categoryEntity = new Category();
        categoryEntity.setName(category.getName());
        categoryEntity.setCategoryDescription(category.getCategoryDescription());
        
        categoryFacade.create(categoryEntity);
    }
    
}
