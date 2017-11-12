package pl.lodz.p.it.spjava.medcenter.web.account;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.model.Admin;
import pl.lodz.p.it.spjava.medcenter.model.Patient;
import pl.lodz.p.it.spjava.medcenter.model.utils.ContextUtils;

@Named(value = "createPatientPageBean")
@RequestScoped
public class CreatePatientPageBean {

    public CreatePatientPageBean() {
    }

    @Inject
    private AccountSession accountSession;

    private Patient account = new Patient();
    private String passwordRepeat = "";

    public String createPatient() throws AppBaseException{
        if (!(passwordRepeat.equals(account.getPassword()))) {
            ContextUtils.emitInternationalizedMessage("createAdminaForm:passwordRepeat", "passwords.not.matching");
            return null;
        }
        return accountSession.createPatient(account);
    }

    public Patient getAccount() {
        return account;
    }

    public void setAccount(Patient account) {
        this.account = account;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

}
