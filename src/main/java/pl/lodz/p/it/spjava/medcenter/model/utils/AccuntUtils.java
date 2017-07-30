
package pl.lodz.p.it.spjava.medcenter.model.utils;

import pl.lodz.p.it.spjava.medcenter.model.Account;
import pl.lodz.p.it.spjava.medcenter.model.Admin;
import pl.lodz.p.it.spjava.medcenter.model.Doctor;
import pl.lodz.p.it.spjava.medcenter.model.Patient;
import pl.lodz.p.it.spjava.medcenter.model.Reception;


public class AccuntUtils {

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
    
//    public static void przepiszDanePoEdycji(Konto zrodlo, Konto cel) {
//        
//        if (null == zrodlo || null == cel) return;
//        
//        cel.setImie(zrodlo.getImie());
//        cel.setNazwisko(zrodlo.getNazwisko());
//        cel.setEmail(zrodlo.getEmail());
//        cel.setTelefon(zrodlo.getTelefon());
//        
//        if (isAdministrator(zrodlo) && isAdministrator(cel)) {
//            Administrator zrodloAdministrator = (Administrator) zrodlo;
//            Administrator celAdministrator = (Administrator) cel;
//            celAdministrator.setAlarmNumber(zrodloAdministrator.getAlarmNumber());
//        }
//        
//        if (isPracownik(zrodlo) && isPracownik(cel)) {
//            Pracownik zrodloPracownik = (Pracownik) zrodlo;
//            Pracownik celPracownik = (Pracownik) cel;
//            celPracownik.setIntercom(zrodloPracownik.getIntercom());
//        }
//        
//        if (isKlient(zrodlo) && isKlient(cel)) {
//            Klient zrodloKlient = (Klient) zrodlo;
//            Klient celKlient = (Klient) cel;
//            celKlient.setNip(zrodloKlient.getNip());
//        }
//    }
//    
//    public static String wyliczSkrotHasla(String hasloJawne){
//        //TODO: wstawić algorytm skrótu hasła
//        return hasloJawne;
//    }

}
