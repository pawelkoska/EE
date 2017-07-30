package pl.lodz.p.it.spjava.medcenter.web.account;

import java.io.Serializable;
import java.security.Principal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import pl.lodz.p.it.spjava.medcenter.model.Account;
import pl.lodz.p.it.spjava.medcenter.model.Admin;
import pl.lodz.p.it.spjava.medcenter.model.Doctor;
import pl.lodz.p.it.spjava.medcenter.model.Patient;
import pl.lodz.p.it.spjava.medcenter.model.Reception;
import pl.lodz.p.it.spjava.medcenter.model.utils.ContextUtils;

@Named(value = "accountSession")
@SessionScoped
public class AccountSession implements Serializable {

    public String getLogin() {
        Principal p = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        return (null == p ? "Guest" : p.getName());
    }

    public String logOut() {
        ContextUtils.invalidateSession();
        return "logOut";
    }

    public static boolean isAdmin(Account account) {
        return (account instanceof Admin);
    }

    public static boolean isDoctor(Account account) {
        return (account instanceof Doctor);
    }

    public static boolean isPatient(Account account) {
        return (account instanceof Patient);
    }

    public static boolean isReception(Account account) {
        return (account instanceof Reception);
    }

}
