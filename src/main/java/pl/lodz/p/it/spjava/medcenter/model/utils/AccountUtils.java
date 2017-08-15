
package pl.lodz.p.it.spjava.medcenter.model.utils;

import pl.lodz.p.it.spjava.medcenter.model.Account;
import pl.lodz.p.it.spjava.medcenter.model.Admin;
import pl.lodz.p.it.spjava.medcenter.model.Doctor;
import pl.lodz.p.it.spjava.medcenter.model.Patient;
import pl.lodz.p.it.spjava.medcenter.model.Reception;


public class AccountUtils {

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
    
    public static void rewriteDateAfterEdition(Account source, Account target) {
        
        if (null == source || null == target) return;
        
        target.setName(source.getName());
        target.setSecondName(source.getSecondName());
        target.setEmail(source.getEmail());
        target.setPhoneNumber(source.getPhoneNumber());
        target.setPesel(source.getPesel());
        
        if (isDoctor(source) && isDoctor(target)) {
            Doctor sourceDoctor = (Doctor) source;
            Doctor targetDoctor = (Doctor) target;
            targetDoctor.setSpecialization(sourceDoctor.getSpecialization());
        }
    }
//    
//    public static String wyliczSkrotHasla(String hasloJawne){
//        //TODO: wstawić algorytm skrótu hasła
//        return hasloJawne;
//    }

}
