package pl.lodz.p.it.spjava.medcenter.web;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import pl.lodz.p.it.spjava.medcenter.dto.CategoryDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.CategoryEndpoint;


@Named(value = "createCategory")
@RequestScoped
public class CreateCategory {

    @EJB
    private CategoryEndpoint categoryEndpoint;

    private CategoryDTO categoryDto;

    public CategoryDTO getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDTO categoryDto) {
        this.categoryDto = categoryDto;
    }

    public CreateCategory() {
        categoryDto = new CategoryDTO();
    }

    public String createCategory() {
        categoryEndpoint.createCategory(categoryDto);
        return "success";
    }
}
