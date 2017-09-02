package pl.lodz.p.it.spjava.medcenter.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ACCOUNT")
@SecondaryTable(name = "PERSONAL_DATA")
@NamedQueries({
    @NamedQuery(name = "Account.getAllDoctors", query = "SELECT d FROM Account d WHERE d.type = 'Doctor'"),
    @NamedQuery(name = "Account.getAllPatients", query = "SELECT p FROM Account p WHERE p.type = 'Patient'"),
    @NamedQuery(name = "Account.getAllAccounts", query = "SELECT a FROM Account a")
})
//@TableGenerator(name = "AccountIdGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "Account", initialValue = 100)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@DiscriminatorValue("ACCOUNT")
public class Account extends AbstractEntity implements Serializable {

    public Account() {
    }

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull(message = "{constraint.notnull}")
    @Size(min = 3, max = 32, message = "{constraint.string.length.notinrange}")
//    @Pattern(regexp = "^[a-z0-9]$", message = "{constraint.string.incorrectchar}")
    @Column(name = "LOGIN", length = 32, nullable = false, unique = true, updatable = false)
    private String login;

    @NotNull(message = "{constraint.notnull}")
    @Size(min = 6, message = "{constraint.string.length.tooshort}")
    @Column(name = "PASSWORD", length = 256, nullable = false)
    private String password;

    @NotNull(message = "{constraint.notnull}")
    @Size(min = 11, max = 11, message = "{constraint.string.length.tooshort}")
    @Column(name = "pesel", table = "PERSONAL_DATA", length = 11, nullable = false)
    private String pesel;

    @Column(name = "confirmed", nullable = false)
    private boolean confirmed;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "TYPE", updatable = false)
    private String type;

    @NotNull(message = "{constraint.notnull}")
    @Size(min = 3, max = 32, message = "{constraint.string.length.notinrange}")
    @Column(name = "name", table = "PERSONAL_DATA", length = 32, nullable = false)
    private String name;

    @NotNull(message = "{constraint.notnull}")
    @Size(min = 3, max = 32, message = "{constraint.string.length.notinrange}")
    @Column(name = "secondName", table = "PERSONAL_DATA", length = 32, nullable = false)
    private String secondName;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "birthDate", table = "PERSONAL_DATA", nullable = false)
    private Date birthDate;

    @NotNull(message = "{constraint.notnull}")
    @Size(min = 6, max = 64, message = "{constraint.string.length.notinrange}")
    @Pattern(regexp = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$", message = "{constraint.string.incorrectemail}")
    @Column(name = "email", table = "PERSONAL_DATA", length = 64, unique = true, nullable = false)
    private String email;

    @Size(max = 12, message = "{constraint.string.length.toolong}")
    @Column(name = "phoneNumber", table = "PERSONAL_DATA", length = 12, unique = true, nullable = true)
    private String phoneNumber;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    protected Object getBusinessKey() {
        return login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
