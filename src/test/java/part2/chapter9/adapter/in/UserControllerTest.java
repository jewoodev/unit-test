package part2.chapter9.adapter.in;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import part2.chapter9.adapter.InAdapterConfig;
import part2.chapter9.adapter.out.CompanyRepository;
import part2.chapter9.adapter.out.UserRepository;
import part2.chapter9.application.required.MessageBus;
import part2.chapter9.domain.Company;
import part2.chapter9.domain.User;
import part2.chapter9.domain.UserType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = InAdapterConfig.class)
class UserControllerTest extends SpringTestSupport {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MessageBus messageBus;

    @Autowired
    private DomainLogger domainLogger;

    @Autowired
    private UserController sut;

    @Autowired
    private EntityManager em;

    @Transactional
    @Test
    void changingEmailFromCorporateToNonCorporate() {
        var company = new Company("my-corp.com", 1);
        var user = new User("user@my-corp.com", UserType.EMPLOYEE, company);
        userRepository.save(user);

        em.flush();
        em.clear();

        String result = sut.changeEmail(user.getUserId(), "user@new-email.com");

        assertThat(result).isEqualTo("OK");

        User userFromDb = userRepository.findByUserId(1L).orElseThrow();
        assertThat(userFromDb.getEmail()).isEqualTo("user@new-email.com");
        assertThat(userFromDb.getUserType()).isEqualTo(UserType.CUSTOMER);

        Company companyFromDB = companyRepository.findByCompanyId(1).orElseThrow();
        assertThat(companyFromDB.getNumberOfEmployees()).isEqualTo(0);

        verify(messageBus).sendEmailChangedMessage(eq(userFromDb.getUserId()), eq(userFromDb.getEmail()));
        verify(domainLogger).userTypeHasChanged(eq(userFromDb.getUserId()), eq(user.getUserType()), eq(userFromDb.getUserType()));
    }
}