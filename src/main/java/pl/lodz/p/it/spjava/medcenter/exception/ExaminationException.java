/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.it.spjava.medcenter.exception;
import pl.lodz.p.it.spjava.medcenter.model.Examination;

/**
 *
 * @author pawkos
 */
public class ExaminationException extends AppBaseException {

    static final public String KEY_DB_CONSTRAINT = "error.examination.db.unique.name";

    private ExaminationException(String message) {
        super(message);
    }

    private ExaminationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    private Examination examination;

    public Examination getExamination() {
        return examination;
    }

    static public ExaminationException createWithDbCheckConstraintKey(Examination examination, Throwable cause) {
        ExaminationException ze = new ExaminationException(KEY_DB_CONSTRAINT, cause);
        ze.examination=examination;
        return ze;
    }
}
