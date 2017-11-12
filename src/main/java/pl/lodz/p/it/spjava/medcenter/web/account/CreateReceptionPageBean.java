package pl.lodz.p.it.spjava.medcenter.web.account;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.model.Admin;
import pl.lodz.p.it.spjava.medcenter.model.Patient;
import pl.lodz.p.it.spjava.medcenter.model.Reception;
import pl.lodz.p.it.spjava.medcenter.model.utils.ContextUtils;

@Named(value = "createReceptionPageBean")
@RequestScoped
public class CreateReceptionPageBean {

    public CreateReceptionPageBean() {
    }

    @Inject
    private AccountSession accountSession;

    private Reception account = new Reception();
    private String passwordRepeat = "";

    public String createReception() throws AppBaseException{
        if (!(passwordRepeat.equals(account.getPassword()))) {
            ContextUtils.emitInternationalizedMessage("createDoctorForm:passwordRepeat", "passwords.not.matching");
            return null;
        }
        return accountSession.createReception(account);
    }

    public Reception getAccount() {
        return account;
    }

    public void setAccount(Reception account) {
        this.account = account;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

}
