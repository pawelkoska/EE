/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.it.spjava.medcenter.exception;

import pl.lodz.p.it.spjava.medcenter.model.Account;

/**
 *
 * @author pawkos
 */
public class AccountException extends AppBaseException {

    static final public String KEY_DB_CONSTRAINT = "error.account.db.unique.name";

    private AccountException(String message) {
        super(message);
    }

    private AccountException(String message, Throwable cause) {
        super(message, cause);
    }
    
    private Account account;

    public Account getAccount() {
        return account;
    }
    
    static public AccountException createWithDbCheckConstraintKey(Account account, Throwable cause) {
        AccountException ze = new AccountException(KEY_DB_CONSTRAINT, cause);
        ze.account=account;
        return ze;
    }
}
