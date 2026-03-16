package part2.chapter9.adapter.in;

import jakarta.transaction.Transactional;
import part2.chapter9.adapter.out.CompanyRepository;
import part2.chapter9.adapter.out.UserRepository;
import part2.chapter9.application.required.MessageBus;
import part2.chapter9.domain.Company;
import part2.chapter9.domain.User;

public class UserController {
    private final EventDispatcher eventDispatcher;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public UserController(MessageBus messageBus, DomainLogger domainLogger, UserRepository userRepository, CompanyRepository companyRepository) {
        this.eventDispatcher = new EventDispatcher(messageBus, domainLogger);
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public String changeEmail(Long userId, String freshEmail) {
        User user = userRepository.findByUserId(userId).orElseThrow();

        user.canChangeEmail();

        Company company = user.getCompany();

        user.changeEmail(freshEmail, company);

        eventDispatcher.dispatch(user.getDomainEvents());

        return "OK";
    }
}
