package part2.chapter9.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static part2.chapter9.domain.UserType.CUSTOMER;
import static part2.chapter9.domain.UserType.EMPLOYEE;


@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private boolean isEmailConfirmed;

    @Transient
    private List<UserDomainEvent> domainEvents;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "fk_user_company"))
    private Company company;

    protected User() {
    }

    public User(String email, UserType userType, Company company) {
        this.email = email;
        this.userType = userType;
        this.company = company;
        this.domainEvents = new ArrayList<>();
    }

    public void canChangeEmail() {
        if (this.isEmailConfirmed)
            throw new IllegalStateException("Can't change a confirmed email.");
    }

    public void changeEmail(String freshEmail, Company company) {
        if (this.email == freshEmail)
            throw new IllegalArgumentException("Can't update with same email.");

        UserType freshType = company.isEmailCorporate(freshEmail)
                ? EMPLOYEE : CUSTOMER;

        if (this.userType != freshType) {
            int delta = freshType == EMPLOYEE ? 1 : -1;
            company.changeNumberOfEmployees(delta);
            this.domainEvents.add(new UserTypeChangedEvent(this.userId, this.userType, freshType));
        }

        this.email = freshEmail;
        this.userType = freshType;
        this.domainEvents.add(new EmailChangedEvent(this.userId, freshEmail));
    }

    // ↓ getter
    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public List<UserDomainEvent> getDomainEvents() {
        return domainEvents;
    }

    public Company getCompany() {
        return company;
    }
}
