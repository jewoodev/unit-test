package part2.chapter8;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import part2.chapter8.adapter.in.UserController;
import part2.chapter8.adapter.out.CompanyRepository;
import part2.chapter8.adapter.out.UserRepository;
import part2.chapter8.application.required.MessageBus;
import part2.chapter8.domain.Company;
import part2.chapter8.domain.User;
import part2.chapter8.domain.UserType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = IntegratedTestConfig.class)
class UserControllerTest extends SpringTestSupport {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @MockitoBean
    private MessageBus messageBus;

    @Autowired
    private UserController sut;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void changingEmailFromCorporateToNonCorporate() {
        // arrange
        var company = new Company("my-corp.com", 1);
        var user = new User("user@my-corp.com", UserType.EMPLOYEE, company);
        User saved = userRepository.save(user);
        entityManager.flush();
        entityManager.clear();

        User foundUser = userRepository.findByUserId(saved.getUserId()).get(0);
        Company foundCompany = companyRepository.findByCompanyId(foundUser.getCompany().getCompanyId()).get(0);

        String freshEmail = "user@new-email.com";

        // act
        String result = sut.changeEmail(foundUser.getUserId(), freshEmail);

        // assert
        assertThat(result).isEqualTo("OK");
        assertThat(foundUser.getEmail()).isEqualTo(freshEmail);
        assertThat(foundUser.getUserType()).isEqualTo(UserType.CUSTOMER);
        assertThat(foundCompany.getNumberOfEmployees()).isEqualTo(0);
        verify(messageBus).sendEmailChangedMessage(eq(foundUser.getUserId()), eq(freshEmail));
    }
}
