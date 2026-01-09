package part2.chapter7.adapter.in;

import part2.chapter7.application.required.Database;
import part2.chapter7.application.required.MessageBus;
import part2.chapter7.domain.User;
import part2.chapter7.domain.UserType;

public class UserController {
    private final Database database;
    private final MessageBus messageBus;

    public UserController(Database database, MessageBus messageBus) {
        this.database = database;
        this.messageBus = messageBus;
    }

    public void changeEmail(int userId, String freshEmail) {
        var data = database.getUserById(userId);
        String email = (String) data[0];
        UserType type = (UserType) data[1];
        var user = new User(userId, freshEmail, type);

        Object[] companyData = database.getCompany();
        String companyDomainName = (String) companyData[0];
        int numberOfEmployees = (int) companyData[1];

        int freshNumberOfEmployees = user.changeEmail(freshEmail, companyDomainName, numberOfEmployees);

        database.saveCompany(freshNumberOfEmployees);
        database.saveUser(user);
        messageBus.sendEmailChangedMessage(userId, freshEmail);
    }
}
