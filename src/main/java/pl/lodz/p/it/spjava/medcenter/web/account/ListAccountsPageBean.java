package pl.lodz.p.it.spjava.medcenter.web.account;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.endpoint.AccountEndpoint;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.model.Account;

@Named(value = "listAccountPageBean")
@RequestScoped
public class ListAccountsPageBean {

    public ListAccountsPageBean() {
    }

    @Inject
    private AccountSession accountSession;
    
    @EJB
    private AccountEndpoint accountEndpoint;

    private List<Account> accountObjList = new ArrayList<>();
    
    private List<Account> accountsListToConfirm = new ArrayList<>();
    private Account confirmedAccount = new Account();
    
    private List<String> accountNameList = new ArrayList<>();
    private List<String> doctorNameList = new ArrayList<>();

    @PostConstruct
    public void getAllAccounts() {
        List<Account> allAccounts = accountSession.getAllAccounts();
        for (Account account : allAccounts) {
            if(account.getType().equals("Doctor")){
                doctorNameList.add(account.getSecondName());
            }
            if(!account.isConfirmed()){
                accountsListToConfirm.add(account);
            }
            accountObjList.add(account);
            accountNameList.add(account.getName());
        }
    }
    private static final Logger LOG = Logger.getLogger(ListAccountsPageBean.class.getName());
    
    public String confirmRegistration(Account account) throws AppBaseException{
        confirmedAccount = accountEndpoint.getAccountById(account);
        confirmedAccount.setActive(true);
        confirmedAccount.setConfirmed(true);
        accountEndpoint.saveAccountAfterConfiramtion(confirmedAccount);
        
        
        return "confirmRegistration";
    }

    public List<Account> getAccountsListToConfirm() {
        return accountsListToConfirm;
    }

    public void setAccountsListToConfirm(List<Account> accountsToConfirm) {
        this.accountsListToConfirm = accountsToConfirm;
    }
    
    

    public List<Account> getAccountObjList() {
        return accountObjList;
    }

    public void setAccountObjList(List<Account> accountObjList) {
        this.accountObjList = accountObjList;
    }
    public String deleteAccount(Account account) {
        accountEndpoint.deleteAccount(account);
        return "deleteAccountSuccess";
    }

    public List<String> getDoctorNameList() {
        return doctorNameList;
    }

    public List<String> getAccountNameList() {
        return accountNameList;
    }

    public void setAccountNameList(List<String> accountNameList) {
        this.accountNameList = accountNameList;
    }
    
    
}
