package pl.lodz.p.it.spjava.medcenter.web.examination;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.model.Category;
import pl.lodz.p.it.spjava.medcenter.model.Examination;

@Named(value = "listExaminationPageBean")
@RequestScoped
public class ListExaminationPageBean {

    public ListExaminationPageBean() {
    }

    @Inject
    private ExaminationSession examinationSession;

    private List<Examination> examinationObjList = new ArrayList<>();
    private List<String> examinationNameList = new ArrayList<>();

    @PostConstruct
    public void getAllExaminations() {
        List<Examination> allExaminations = examinationSession.getAllExaminations();
        for (Examination examination : allExaminations) {
            examinationObjList.add(examination);
            examinationNameList.add(examination.getName());
        }
    }

    public List<Examination> getExaminationObjList() {
        return examinationObjList;
    }

    public void setExaminationObjList(List<Examination> examinationObjList) {
        this.examinationObjList = examinationObjList;
    }

    public List<Examination> getExaminationsByCategory(Category category) {
        return examinationSession.getExaminationsByCategory(category);
    }

    public List<String> getExaminationNameList() {
        return examinationNameList;
    }

    public void setExaminationNameList(List<String> examinationNameList) {
        this.examinationNameList = examinationNameList;
    }

}
