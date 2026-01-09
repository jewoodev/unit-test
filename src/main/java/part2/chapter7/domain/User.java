package part2.chapter7.domain;

import part2.chapter7.application.required.Database;

import static part2.chapter7.domain.UserType.CUSTOMER;
import static part2.chapter7.domain.UserType.EMPLOYEE;

public class User {
    public int userId;
    public String email;
    public UserType userType;

    public User(int userId, String email, UserType userType) {
        this.userId = userId;
        this.email = email;
        this.userType = userType;
    }

    public int changeEmail(String freshEmail, String companyDomainName, int numberOfEmployees) {
        Object[] data = database.getUserById(userId);

        if (this.email == freshEmail)
            throw new IllegalArgumentException("Can't update with same email.");

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

        return numberOfEmployees;
    }


    private Database database; // 코드 구현만을 위한 임시 필드
}
