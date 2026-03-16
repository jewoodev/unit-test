package part2.chapter9.adapter;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import part2.chapter9.adapter.in.DomainLogger;
import part2.chapter9.adapter.in.UserController;
import part2.chapter9.adapter.out.CompanyRepository;
import part2.chapter9.adapter.out.UserRepository;
import part2.chapter9.application.required.MessageBus;

@Configuration
public class InAdapterConfig {
    @Bean
    public MessageBus messageBus() {
        return new MessageBus();
    }

    @Bean
    public DomainLogger domainLogger() {
        return new DomainLogger(LoggerFactory.getLogger("UserDomainLogger"));
    }

    @Bean
    public UserController userController(MessageBus messageBus, DomainLogger domainLogger, UserRepository userRepository, CompanyRepository companyRepository) {
        return new UserController(messageBus, domainLogger, userRepository, companyRepository);
    }
}
