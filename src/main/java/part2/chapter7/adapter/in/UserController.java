package part2.chapter7.adapter.in;

import part2.chapter7.application.required.Database;
import part2.chapter7.application.required.MessageBus;
import part2.chapter7.application.service.CompanyFactory;
import part2.chapter7.application.service.UserFactory;
import part2.chapter7.domain.Company;
import part2.chapter7.domain.User;

public class UserController {
    private final Database database;
    private final MessageBus messageBus;

    public UserController(Database database, MessageBus messageBus) {
        this.database = database;
        this.messageBus = messageBus;
    }

    public String changeEmail(int userId, String freshEmail) {
        Object[] userData = database.getUserById(userId);
        User user = UserFactory.create(userData);

        user.canChangeEmail();

        Object[] companyData = database.getCompany();
        Company company = CompanyFactory.create(companyData);

        user.changeEmail(freshEmail, company);

        database.saveCompany(company);
        database.saveUser(user);
        messageBus.sendEmailChangedMessage(userId, freshEmail);

        return "OK";
    }
}
