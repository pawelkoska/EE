package pl.lodz.p.it.spjava.medcenter.web.appointment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.endpoint.AppointmentEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Appointment;

@Named(value = "listAppointmentPageBean")
@RequestScoped
public class listAppointmentPageBean {

    private static final Logger LOG = Logger.getLogger(listAppointmentPageBean.class.getName());

    public listAppointmentPageBean() {
    }

    @Inject
    private AppointmentSession appointmentSession;

    @EJB
    private AppointmentEndpoint appointmentEndpoint;

    private List<Appointment> appointmentObjList = new ArrayList<>();

    private List<Appointment> myAppointmentList;
    private List<Appointment> myMedicalHistory;

    private List<Appointment> myGeneralSheduleList;
    private List<Appointment> doctorMedicalHistory;
    private List<Appointment> myDailyShedule;

    @PostConstruct
    public void init() {
        List<Appointment> allAppointments = appointmentEndpoint.getAllAppointments();
        for (Appointment a : allAppointments) {
            appointmentObjList.add(a);
        }
    }

    public List<Appointment> getAppointmentObjList() {
        return appointmentObjList;
    }

    public void setAppointmentObjList(List<Appointment> appointmentObjList) {
        this.appointmentObjList = appointmentObjList;
    }

    public List<Appointment> getMyAppointments() {
        myAppointmentList = null;
        myAppointmentList = new ArrayList<>();
        String userName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        for (Appointment a : appointmentEndpoint.getAllAppointments()) {
            if (a.getPatientId() != null && a.getPatientId().getLogin().equals(userName) && a.getAppointmentResult() == null) {
                myAppointmentList.add(a);
            }
        }
        return myAppointmentList;
    }

    public List<Appointment> getMyGeneralSheduleList() {
        myGeneralSheduleList = null;
        myGeneralSheduleList = new ArrayList<>();
        String userName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        for (Appointment a : appointmentEndpoint.getAllAppointments()) {
            if (a.getDoctorId() != null && a.getDoctorId().getLogin().equals(userName) && a.getAppointmentResult() == null) {
                myGeneralSheduleList.add(a);
            }
        }
        return myGeneralSheduleList;
    }

    public void setMyGeneralSheduleList(List<Appointment> myGeneralSheduleList) {
        this.myGeneralSheduleList = myGeneralSheduleList;
    }

    public List<Appointment> getDoctorMedicalHistory() {
        doctorMedicalHistory = null;
        doctorMedicalHistory = new ArrayList<>();
        String userName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        for (Appointment a : appointmentEndpoint.getAllAppointments()) {
            if (a.getDoctorId().getLogin().equals(userName) && a.getAppointmentResult() != null) {
                doctorMedicalHistory.add(a);
            }
        }
        return doctorMedicalHistory;
    }

    public List<Appointment> getMyMedicalHistory() {
        myMedicalHistory = null;
        myMedicalHistory = new ArrayList<>();
        String userName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        for (Appointment a : appointmentEndpoint.getAllAppointments()) {
            if (a.getPatientId() != null && a.getPatientId().getLogin().equals(userName) && a.getAppointmentResult() != null) {
                myMedicalHistory.add(a);
            }
        }
        return myMedicalHistory;
    }

    public List<Appointment> getMyDailyShedule() {

        myDailyShedule = null;
        myDailyShedule = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDate = dateFormat.format(new Date());

        String doctorName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        for (Appointment a : appointmentEndpoint.getAllAppointments()) {
            if (a.getPatientId() != null && a.getDoctorId().getLogin().equals(doctorName) && a.getAppointmentResult() == null
                    && currentDate.equals(dateFormat.format(a.getDate()))) {
                myDailyShedule.add(a);
            }
        }
        return myDailyShedule;
    }

}
