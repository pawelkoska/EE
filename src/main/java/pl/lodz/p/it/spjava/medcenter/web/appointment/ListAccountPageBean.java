package pl.lodz.p.it.spjava.medcenter.web.appointment;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.model.Doctor;
import pl.lodz.p.it.spjava.medcenter.model.Patient;
import pl.lodz.p.it.spjava.medcenter.web.account.AccountSession;

@Named(value = "listAccountPageBean")
@RequestScoped
public class ListAccountPageBean {

    public ListAccountPageBean() {
    }

    @Inject
    private AccountSession accountSession;

    private List<Doctor> doctorObjList = new ArrayList<>();

//    @PostConstruct
//    public void getAllAccounts() {
//        List<Account> allExaminations = accountSession.getAllExaminations();
//        for (Account examination : allExaminations) {
//            accountObjList.add(examination);
//        }
//    }


  
    public void getAllDoctors() {
        List<Doctor> allDoctors = accountSession.getAllDoctors();
        for (Doctor d : allDoctors) {
            doctorObjList.add(d);
        }
    }

    public List<Doctor> getDoctorObjList() {
        return doctorObjList;
    }

    public void setDoctorObjList(List<Doctor> doctorObjList) {
        this.doctorObjList = doctorObjList;
    }
    
    public List<Patient> getAllPatients() {
        return accountSession.getAllPatients();
    }

}
