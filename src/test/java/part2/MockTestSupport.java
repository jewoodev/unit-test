package part2;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import part2.chapter5.application.required.EmailGateway;
import part2.chapter5.application.required.CustomerRepository;
import part2.chapter5.application.required.ProductRepository;
import part2.chapter5.domain.Store;

@ExtendWith(MockitoExtension.class)
public abstract class MockTestSupport extends GlobalTestSupport {
    @Mock
    protected CustomerRepository customerRepository;

    @Mock
    protected ProductRepository productRepository;

    @Mock
    protected EmailGateway emailGateway;

    @Mock
    protected Store store;
}
