package part2.chapter6;

import org.junit.jupiter.api.Test;
import part2.MockTestSupport;
import part2.chapter5.adapter.in.api.CustomerApi;
import part2.chapter5.domain.Customer;

import static org.mockito.Mockito.verify;

class EmailTest extends MockTestSupport {
    @Test
    void sendingGreetingEmail() {
        var customer = new Customer(super.store, "John Doe");
        var sut = new CustomerApi(super.customerRepository, super.productRepository, super.emailGateway);

        sut.greeting(customer);

        verify(super.emailGateway).sendGreeting(customer.getEmailAddress());
    }
}
