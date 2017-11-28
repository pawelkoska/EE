package pl.lodz.p.it.spjava.medcenter.dto;

/**
 *
 * @author pawkos
 */
public class ExaminationDTO {

    public ExaminationDTO(String name, String examinationDescription) {
        this.name = name;
        this.examinationDescription = examinationDescription;
    }

    private String name;
    private String examinationDescription;
    private String examinationCategory;
    private int examinationDuration;

    public int getExaminationDuration() {
        return examinationDuration;
    }

    public void setExaminationDuration(int examinationDuration) {
        this.examinationDuration = examinationDuration;
    }

    public String getExaminationCategory() {
        return examinationCategory;
    }

    public void setExaminationCategory(String examinationCategory) {
        this.examinationCategory = examinationCategory;
    }

    public ExaminationDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExaminationDescription() {
        return examinationDescription;
    }

    public void setExaminationDescription(String description) {
        this.examinationDescription = description;
    }

    @Override
    public String toString() {
        return "ExaminationDTO{" + "name=" + name + ", examinationDescription=" + examinationDescription + ", examinationCategory=" + examinationCategory + ", examinationDuration=" + examinationDuration + '}';
    }

}
