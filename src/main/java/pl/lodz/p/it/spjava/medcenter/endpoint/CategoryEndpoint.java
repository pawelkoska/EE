package pl.lodz.p.it.spjava.medcenter.endpoint;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.exception.CategoryException;
import pl.lodz.p.it.spjava.medcenter.exception.GeneralOptimisticLockException;
import pl.lodz.p.it.spjava.medcenter.facade.CategoryFacade;
import pl.lodz.p.it.spjava.medcenter.interceptor.LoggingInterceptor;
import pl.lodz.p.it.spjava.medcenter.model.Category;
import pl.lodz.p.it.spjava.medcenter.model.utils.ContextUtils;

/**
 *
 * @author pawkos
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class CategoryEndpoint {

    @EJB
    private CategoryFacade categoryFacade;

    public String createCategory(Category category) throws AppBaseException{
        try{
            categoryFacade.create(category);  
            ContextUtils.emitSuccessMessage("createCategory");
            return "allCategories";
        }catch(CategoryException ce){
            ContextUtils.emitInternationalizedMessage(null, CategoryException.KEY_DB_CONSTRAINT);
        }
        return null;
    }

    public List<Category> getAllCategories() {
        return categoryFacade.findAll();
    }

    public Category getCategoryToEdit(Category category) {
        Category categoryEntity = categoryFacade.find(category.getId());
        return categoryEntity;
    }

    public void saveEditedCategory(Category c) throws AppBaseException {
        try{
            categoryFacade.edit(c);   
            ContextUtils.emitSuccessMessage("");
        } catch (GeneralOptimisticLockException ce){
            ContextUtils.emitInternationalizedMessage(null, GeneralOptimisticLockException.KEY_OPTIMISTIC_LOCK);
        }
    }

    public String deleteCategory(Category category) {
        categoryFacade.remove(category);
        return "deleteCategorySuccess";
    }

}
