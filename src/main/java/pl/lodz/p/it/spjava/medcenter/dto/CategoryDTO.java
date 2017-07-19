package pl.lodz.p.it.spjava.medcenter.dto;

/**
 *
 * @author pawkos
 */
public class CategoryDTO {
    
    public CategoryDTO(String name, String categoryDescription) {
        this.name = name;
        this.categoryDescription = categoryDescription;
    }
    
    private String name;
    private String categoryDescription;

    public CategoryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String description) {
        this.categoryDescription = description;
    }
}
