package pl.lodz.p.it.spjava.medcenter.endpoint;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.facade.AccountFacade;
import pl.lodz.p.it.spjava.medcenter.model.Account;
import pl.lodz.p.it.spjava.medcenter.model.Doctor;
import pl.lodz.p.it.spjava.medcenter.model.Patient;
import pl.lodz.p.it.spjava.medcenter.model.utils.AccountUtils;

/**
 *
 * @author pawkos
 */
@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AccountEndpoint {

    @Inject
    private AccountFacade accountFacade;

    @Resource
    protected SessionContext sctx;

    private Account accountEditHandler;

    public void createAccount(Account account) {
        account.setActive(true);
        account.setConfirmed(true);
        accountFacade.create(account);
    }

    public Account getAccountToEdit(Account account) {
        accountEditHandler = accountFacade.findLogin(account.getLogin());
        return accountEditHandler;
    }
    
    public Account getMyAccount() {
        return accountFacade.findLogin(sctx.getCallerPrincipal().getName());
    }

    public void saveEditedAccount(Account account) {
        if (null == accountEditHandler) {
            throw new IllegalArgumentException("No loaded account to edit");
        }
        if (!accountEditHandler.equals(account)) {
            throw new IllegalArgumentException("Modifying account conflict with laoded account");
        }
        AccountUtils.rewriteDateAfterEdition(account, accountEditHandler);
        accountFacade.edit(accountEditHandler);
        accountEditHandler = null;
    }

    public void deleteAccount(Account account) {
        accountFacade.remove(account);
    }

    public List<Account> getAllAccounts() {
        return accountFacade.getAllAccounts();
    }

    public List<Doctor> getAllDoctors() {
        return accountFacade.getAllDoctors();
    }

    public List<Patient> getAllPatients() {
        return accountFacade.getAllPatients();
    }

}
