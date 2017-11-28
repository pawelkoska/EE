package pl.lodz.p.it.spjava.medcenter.web.examination;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import pl.lodz.p.it.spjava.medcenter.endpoint.ExaminationEndpoint;
import pl.lodz.p.it.spjava.medcenter.model.Examination;

@Named(value = "deleteExaminationPageBean")
@RequestScoped
public class DeleteExaminationPageBean {

    public DeleteExaminationPageBean() {
    }

    @EJB
    private ExaminationEndpoint examinationEndpoint;

    public String deleteExamination(Examination examination) {
        examinationEndpoint.deleteExamination(examination);
        return "deleteExaminationSuccess";
    }

}
