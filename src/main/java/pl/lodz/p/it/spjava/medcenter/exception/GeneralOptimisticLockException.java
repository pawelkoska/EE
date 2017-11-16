/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.it.spjava.medcenter.exception;

import javax.persistence.OptimisticLockException;

/**
 *
 * @author pawkos
 */
public class GeneralOptimisticLockException extends AppBaseException {

    static final public String KEY_OPTIMISTIC_LOCK = "error.general.optimisticlock";

    private GeneralOptimisticLockException(String message) {
        super(message);
    }

    private GeneralOptimisticLockException(String message, Throwable cause) {
        super(message, cause);
    }

    private GeneralOptimisticLockException gole;

    public GeneralOptimisticLockException getGeneralOptimisticLockException() {
        return gole;
    }

    static public GeneralOptimisticLockException createWithOptimisticLockKey(OptimisticLockException cause) {
        GeneralOptimisticLockException ze = new GeneralOptimisticLockException(KEY_OPTIMISTIC_LOCK, cause);
        return ze;
    }

}
