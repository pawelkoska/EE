package pl.lodz.p.it.spjava.medcenter.endpoint;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.medcenter.facade.AccountFacade;
import pl.lodz.p.it.spjava.medcenter.facade.CategoryFacade;
import pl.lodz.p.it.spjava.medcenter.model.Account;
import pl.lodz.p.it.spjava.medcenter.model.Admin;
import pl.lodz.p.it.spjava.medcenter.model.Category;
import pl.lodz.p.it.spjava.medcenter.model.Doctor;
import pl.lodz.p.it.spjava.medcenter.model.Patient;

/**
 *
 * @author pawkos
 */
@Stateful
public class AccountEndpoint {

    @Inject
    private AccountFacade accountFacade;
    
    public void createAccount(Account account) {
        account.setActive(true);
        account.setConfirmed(true);
        accountFacade.create(account);
    }


//    public void createCategory(Category category) {
//        categoryFacade.create(category);
//    }
//
//    public List<Category> getAllCategories() {
//        return categoryFacade.findAll();
//    }
//
//    public Category getCategoryToEdit(Category category) {
//        Category categoryEntity = categoryFacade.find(category.getId());
////        kontoFacade.refresh(encja);
//        return categoryEntity;
//    }
//
//    public void saveEditedCategory(Category c) {
//        categoryFacade.edit(c);
//    }
//
//    public String deleteCategory(Category category) {
//        categoryFacade.remove(category);
//        return "deleteCategorySuccess";
//    }
    
    public List<Account> getAllAccounts(){
        return accountFacade.getAllAccounts();
    }
    
    public List<Doctor> getAllDoctors() {
        return accountFacade.getAllDoctors();
    }

    public List<Patient> getAllPatients() {
        return accountFacade.getAllPatients();
    }

}
