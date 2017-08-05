package pl.lodz.p.it.spjava.medcenter.web.account;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.model.Account;

@Named(value = "listAccountPageBean")
@RequestScoped
public class ListAccountsPageBean {

    public ListAccountsPageBean() {
    }

    @Inject
    private AccountSession accountSession;

    private List<Account> accountObjList = new ArrayList<>();

    @PostConstruct
    public void getAllExaminations() {
        List<Account> allAccounts = accountSession.getAllAccounts();
        for (Account account : allAccounts) {
            accountObjList.add(account);
        }
    }

    public List<Account> getAccountObjList() {
        return accountObjList;
    }

    public void setAccountObjList(List<Account> accountObjList) {
        this.accountObjList = accountObjList;
    }

}
