package pl.lodz.p.it.spjava.medcenter.dto;

/**
 *
 * @author pawkos
 */
public class DoctorDTO {

    private String category;

    public DoctorDTO() {
    }
    public DoctorDTO(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
