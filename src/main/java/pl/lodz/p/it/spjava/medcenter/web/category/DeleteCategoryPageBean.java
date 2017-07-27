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

@Named(value = "deleteCategoryPageBean")
@RequestScoped
public class DeleteCategoryPageBean {

    public DeleteCategoryPageBean() {
    }

    @EJB
    private CategoryEndpoint categoryEndpoint;


    public String deleteCategory(Category category) {
        categoryEndpoint.deleteCategory(category);
        return "deleteCategorySuccess";
    }

}
