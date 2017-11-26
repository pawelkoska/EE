package pl.lodz.p.it.spjava.medcenter.endpoint;

import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import pl.lodz.p.it.spjava.medcenter.exception.AccountException;
import pl.lodz.p.it.spjava.medcenter.exception.AppBaseException;
import pl.lodz.p.it.spjava.medcenter.exception.GeneralOptimisticLockException;
import pl.lodz.p.it.spjava.medcenter.facade.AccountFacade;
import pl.lodz.p.it.spjava.medcenter.interceptor.LoggingInterceptor;
import pl.lodz.p.it.spjava.medcenter.model.Account;
import pl.lodz.p.it.spjava.medcenter.model.Doctor;
import pl.lodz.p.it.spjava.medcenter.model.Patient;
import pl.lodz.p.it.spjava.medcenter.model.utils.AccountUtils;
import pl.lodz.p.it.spjava.medcenter.model.utils.ContextUtils;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author pawkos
 */
@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class AccountEndpoint {

    @Inject
    private AccountFacade accountFacade;

    @Resource
    protected SessionContext sctx;

    private Account accountEditHandler;
    private static final Logger LOG = Logger.getLogger(AccountEndpoint.class.getName());

    public void createAccount(Account account) throws AppBaseException {
        account.setActive(false);
        account.setConfirmed(false);
        account.setPassword(DigestUtils.sha256Hex(account.getPassword()));
        try {
            accountFacade.create(account);
            ContextUtils.emitSuccessMessage("createAdminForm");
        } catch (AccountException ae) {
            ContextUtils.emitInternationalizedMessage("createAdminForm" , AccountException.KEY_DB_CONSTRAINT);
        }
    }

    public Account getAccountToEdit(Account account) {
        accountEditHandler = accountFacade.findLogin(account.getLogin());
        return accountEditHandler;
    }

    public Account getMyAccount() {
        return accountFacade.findLogin(sctx.getCallerPrincipal().getName());
    }

    public Account getAccountById(Account account) {
        return accountFacade.getAccountById(account);
    }

    public void saveAccountAfterConfiramtion(Account account) throws AppBaseException {

        try {
            accountFacade.edit(account);
            ContextUtils.emitSuccessMessage("");
        } catch (GeneralOptimisticLockException ce) {
            ContextUtils.emitInternationalizedMessage(null, GeneralOptimisticLockException.KEY_OPTIMISTIC_LOCK);
        }

    }

    public void saveEditedAccount(Account account) throws AppBaseException {
        if (null == accountEditHandler) {
            throw new IllegalArgumentException("No loaded account to edit");
        }
        if (!accountEditHandler.equals(account)) {
            throw new IllegalArgumentException("Modifying account conflict with laoded account");
        }
        AccountUtils.rewriteDateAfterEdition(account, accountEditHandler);

        try {
            accountFacade.edit(accountEditHandler);
            ContextUtils.emitSuccessMessage("");
        } catch (GeneralOptimisticLockException ce) {
            ContextUtils.emitInternationalizedMessage(null, GeneralOptimisticLockException.KEY_OPTIMISTIC_LOCK);
        }
        
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
