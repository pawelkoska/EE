package pl.lodz.p.it.spjava.medcenter.web.account;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.medcenter.model.Account;
import pl.lodz.p.it.spjava.medcenter.model.utils.AccountUtils;

/**
 *
 * @author java
 */
@Named("editAccountPageBean")
@RequestScoped
public class EditAccountPageBean {

    public EditAccountPageBean() {
    }

    @Inject
    private AccountSession accountSession;

    private Account account = new Account();

    @PostConstruct
    private void init() {
        account = accountSession.getEditedAccount();
    }

    public String saveAccount() {
        return accountSession.saveEditedAccount(account);
    }

    public String getAccountToEdit(Account account) {
        accountSession.getAccountToEdit(account);
        return "editAccount";
    }

    public Account getAccount() {
        return account;
    }

    public boolean isDoctor() {
        return AccountUtils.isDoctor(account);
    }
}
