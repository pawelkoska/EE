package pl.lodz.p.it.spjava.medcenter.web.account;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import pl.lodz.p.it.spjava.medcenter.endpoint.AccountEndpoint;
import pl.lodz.p.it.spjava.medcenter.facade.CategoryFacade;
import pl.lodz.p.it.spjava.medcenter.model.Account;
import pl.lodz.p.it.spjava.medcenter.model.Admin;
import pl.lodz.p.it.spjava.medcenter.model.Doctor;
import pl.lodz.p.it.spjava.medcenter.model.Patient;
import pl.lodz.p.it.spjava.medcenter.model.Reception;
import pl.lodz.p.it.spjava.medcenter.model.utils.ContextUtils;

@Named(value = "accountSession")
@SessionScoped
public class AccountSession implements Serializable {

    @EJB
    private AccountEndpoint accountEndpoint;

    @EJB
    private CategoryFacade categoryFacade;

    private Admin createAdmin;
    private Patient createPatient;
    private Reception createReception;
    private Doctor createDoctor;
    
    public List<Account> getAllAccounts(){
        return accountEndpoint.getAllAccounts();
    }
    
    
    public List<Doctor> getAllDoctors() {
        return accountEndpoint.getAllDoctors();
    }

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

    public List<Patient> getAllPatients() {
        return accountEndpoint.getAllPatients();
    }

    public String createAdmin(Admin admin) {
        createAdmin = admin;
        accountEndpoint.createAccount(createAdmin);
        createAdmin = null;
        return "successCreateAdmin";
    }

    public String createPatient(Patient patient) {
        createPatient = patient;
        accountEndpoint.createAccount(createPatient);
        createAdmin = null;
        return "successCreatePatient";
    }

    public String createReception(Reception reception) {
        createReception = reception;
        accountEndpoint.createAccount(createReception);
        createReception = null;
        return "successCreateReception";
    }

    public String createDoctor(Doctor doctor) {
        createDoctor = doctor;
        accountEndpoint.createAccount(createDoctor);
        createDoctor = null;
        return "successCreateDoctor";
    }

}
