package part2.chapter7.application.required;

import part2.chapter7.domain.User;

public interface Database {
    Object[] getUserById(int userId);
    Object[] getCompany();
    void saveCompany(int freshNumber);
    void saveUser(User user);
}
