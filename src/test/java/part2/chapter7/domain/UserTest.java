package part2.chapter7.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {
    @CsvSource({"my-corporate.com, user@my-corporate.com, 2, EMPLOYEE",
            "my-corporate.com, user@gmail.com, 1, CUSTOMER"})
    @ParameterizedTest
    void changingEmailFromNonCorporate(String companyDomain, String freshEmail,
                                       int expectedNumOfEmp, UserType expectedUserType
    ) {
        var company = new Company(companyDomain, 1);
        var sut = new User(1, "user@naver.com", UserType.CUSTOMER);

        sut.changeEmail(freshEmail, company);

        assertThat(company.getNumberOfEmployees()).isEqualTo(expectedNumOfEmp);
        assertThat(sut.getEmail()).isEqualTo(freshEmail);
        assertThat(sut.getUserType()).isEqualTo(expectedUserType);
    }

    @Test
    void changingEmailWithSame() {
        var company = new Company("my-corporate.com", 1);
        var sut = new User(1, "user@my-corporate.com", UserType.EMPLOYEE);

        assertThatThrownBy(() -> sut.changeEmail("user@my-corporate.com", company))
                .isInstanceOf(IllegalArgumentException.class);
    }
}