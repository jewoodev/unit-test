package part2.chapter5.application.required;

import part2.chapter5.domain.Product;

public interface EmailGateway {
    void sendReceipt(String recipientEmail, Product product, int quantity);

    void sendGreeting(String emailAddress);
}
