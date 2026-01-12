package part2.chapter8.adapter.in;

import jakarta.transaction.Transactional;
import part2.chapter8.adapter.out.CompanyRepository;
import part2.chapter8.adapter.out.UserRepository;
import part2.chapter8.application.required.MessageBus;
import part2.chapter8.domain.Company;
import part2.chapter8.domain.User;

import java.util.List;

public class UserController {
    private final MessageBus messageBus;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public UserController(MessageBus messageBus, UserRepository userRepository, CompanyRepository companyRepository) {
        this.messageBus = messageBus;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public String changeEmail(Long userId, String freshEmail) {
        List<User> userData = userRepository.findByUserId(userId);

        User user = userData.get(0);
        user.canChangeEmail();

        Company company = user.getCompany();

        user.changeEmail(freshEmail, company);

        messageBus.sendEmailChangedMessage(userId, freshEmail);

        return "OK";
    }
}
