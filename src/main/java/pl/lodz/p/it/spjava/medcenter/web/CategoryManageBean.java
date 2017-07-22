package pl.lodz.p.it.spjava.medcenter.web;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import pl.lodz.p.it.spjava.medcenter.dto.CategoryDTO;
import pl.lodz.p.it.spjava.medcenter.endpoint.CategoryEndpoint;


@Named(value = "CategoryManageBean")
@RequestScoped
public class CategoryManageBean {

    @EJB
    private CategoryEndpoint categoryEndpoint;

    private CategoryDTO categoryDto;

    public CategoryDTO getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDTO categoryDto) {
        this.categoryDto = categoryDto;
    }

    public CategoryManageBean() {
        categoryDto = new CategoryDTO();
    }

    public String createCategory() {
        categoryEndpoint.createCategory(categoryDto);
        return "success";
    }
}
