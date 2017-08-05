package pl.lodz.p.it.spjava.medcenter.web.account;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.dto.DoctorDTO;
import pl.lodz.p.it.spjava.medcenter.facade.CategoryFacade;
import pl.lodz.p.it.spjava.medcenter.model.Category;
import pl.lodz.p.it.spjava.medcenter.model.Doctor;
import pl.lodz.p.it.spjava.medcenter.model.utils.ContextUtils;

@Named(value = "createDoctorPageBean")
@RequestScoped
public class CreateDoctorPageBean {

    public CreateDoctorPageBean() {
    }

    @Inject
    private AccountSession accountSession;

    @EJB
    private CategoryFacade categoryFacade;

    private Doctor account = new Doctor();
    private DoctorDTO doctorDto = new DoctorDTO();

    private String passwordRepeat = "";

    public String createDoctor() {
        if (!(passwordRepeat.equals(account.getPassword()))) {
            ContextUtils.emitInternationalizedMessage("createDoctorForm:passwordRepeat", "passwords.not.matching");
            return null;
        }

        List<Category> categories = categoryFacade.findAll();
        Category selectedCategory = null;
        for (Category category : categories) {
            if (category.getName().equals(doctorDto.getCategory())) {
                selectedCategory = category;
                break;
            }
        }
        if (selectedCategory == null) {
            throw new IllegalArgumentException("wrong category");
        }

        account.setSpecialization(selectedCategory);
        return accountSession.createDoctor(account);
    }

    public Doctor getAccount() {
        return account;
    }

    public void setAccount(Doctor account) {
        this.account = account;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public DoctorDTO getDoctorDto() {
        return doctorDto;
    }

    public void setDoctorDto(DoctorDTO doctorDto) {
        this.doctorDto = doctorDto;
    }

}
