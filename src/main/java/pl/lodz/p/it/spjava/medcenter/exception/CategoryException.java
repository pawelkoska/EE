/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.it.spjava.medcenter.exception;
import pl.lodz.p.it.spjava.medcenter.model.Category;

/**
 *
 * @author pawkos
 */
public class CategoryException extends AppBaseException {

    static final public String KEY_DB_CONSTRAINT = "error.category.db.unique.name";

    private CategoryException(String message) {
        super(message);
    }

    private CategoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
    private Category category;

    public Category getCategory() {
        return category;
    }

    static public CategoryException createWithDbCheckConstraintKey(Category category, Throwable cause) {
        CategoryException ze = new CategoryException(KEY_DB_CONSTRAINT, cause);
        ze.category=category;
        return ze;
    }
}
