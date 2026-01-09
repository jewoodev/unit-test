package part2.chapter7.domain;

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

    public void changeEmail(String freshEmail, Company company) {
        if (this.email == freshEmail)
            throw new IllegalArgumentException("Can't update with same email.");

        UserType freshType = company.isEmailCorporate(freshEmail)
                ? EMPLOYEE : CUSTOMER;

        if (this.userType != freshType) {
            int delta = freshType == EMPLOYEE ? 1 : -1;
            company.changeNumberOfEmployees(delta);
        }

        this.email = freshEmail;
        this.userType = freshType;
    }

}
