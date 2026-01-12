package part2.chapter8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import part2.chapter8.adapter.in.UserController;
import part2.chapter8.adapter.out.CompanyRepository;
import part2.chapter8.adapter.out.UserRepository;
import part2.chapter8.application.required.MessageBus;

@Configuration
public class IntegratedTestConfig {
    @Bean
    public UserController userController(MessageBus messageBus, UserRepository userRepository, CompanyRepository companyRepository) {
        return new UserController(messageBus, userRepository, companyRepository);
    }
}
