package part2.chapter5.adapter.in.api;

import part2.chapter5.application.required.EmailGateway;
import part2.chapter5.application.required.CustomerRepository;
import part2.chapter5.application.required.ProductRepository;
import part2.chapter5.domain.Customer;

public class CustomerApi {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final EmailGateway emailGateway;

    public CustomerApi(CustomerRepository customerRepository, ProductRepository productRepository, EmailGateway emailGateway) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.emailGateway = emailGateway;
    }

    public boolean purchase(int customerId, int productId, int quantity) {
        var customer = this.customerRepository.findById(customerId);
        var product = this.productRepository.findById(productId);

        boolean isSuccess = customer.purchase(product, quantity);

        if (isSuccess) {
            emailGateway.sendReceipt(customer.getEmailAddress(), product, quantity);
        }

        return isSuccess;
    }

    public void greeting(Customer customer) {
        emailGateway.sendGreeting(customer.getEmailAddress());
    }
}
