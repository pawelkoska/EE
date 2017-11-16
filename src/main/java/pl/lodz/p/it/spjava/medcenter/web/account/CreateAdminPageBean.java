package pl.lodz.p.it.spjava.medcenter.web.account;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.model.Admin;
import pl.lodz.p.it.spjava.medcenter.model.utils.ContextUtils;

@Named(value = "createAdminPageBean")
@RequestScoped
public class CreateAdminPageBean {

    public CreateAdminPageBean() {
    }

    @Inject
    private AccountSession accountSession;

    private Admin account = new Admin();
    private String passwordRepeat = "";

    public void createAdmin() throws AppBaseException{
        if (!(passwordRepeat.equals(account.getPassword()))) {
            ContextUtils.emitInternationalizedMessage("createAdminaForm:passwordRepeat", "passwords.not.matching");
            return;
        }
        accountSession.createAdmin(account);
    }

    public Admin getAccount() {
        return account;
    }

    public void setAccount(Admin account) {
        this.account = account;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

}
