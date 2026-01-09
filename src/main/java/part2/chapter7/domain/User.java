package part2.chapter7.domain;

import part2.chapter7.application.required.Database;
import part2.chapter7.application.required.MessageBus;

import static part2.chapter7.domain.UserType.*;

public class User {
    public int userId;
    public String email;
    public UserType userType;

    public User(int userId, String email, UserType userType, Database database) {
        this.userId = userId;
        this.email = email;
        this.userType = userType;
        this.database = database;
    }

    public void changeEmail(int userId, String freshEmail) {
        Object[] data = database.getUserById(userId);

        if (this.email == freshEmail)
            throw new IllegalArgumentException("Can't update with same email.");

        Object[] companyData = database.getCompany();
        String companyDomainName = (String) companyData[0];
        int numberOfEmployees = (int) companyData[1];

        String emailDomain = freshEmail.split("@")[1];
        boolean isEmailCorporate = emailDomain.equals(companyDomainName);
        UserType freshType = companyDomainName.equals("company.com") ? EMPLOYEE : CUSTOMER;

        if (this.userType == freshType) {
            int delta = freshType == EMPLOYEE ? 1 : -1;
            int freshNumber = numberOfEmployees + delta;
            database.saveCompany(freshNumber);
        }

        this.email = freshEmail;
        this.userType = freshType;

        database.saveUser(this);
        MessageBus.sendEmailChangedMessage(userId, freshEmail);
    }


    private Database database;
}
