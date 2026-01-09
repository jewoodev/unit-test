package part2.chapter7.application.service;

import part2.chapter7.domain.User;
import part2.chapter7.domain.UserType;

public class UserFactory {
    public static User create(Object[] data) {
        Precondition.requires(data.length == 3);

        int id = (int) data[0];
        String email = (String) data[1];
        UserType userType = (UserType) data[2];

        return new User(id, email, userType);
    }
}
