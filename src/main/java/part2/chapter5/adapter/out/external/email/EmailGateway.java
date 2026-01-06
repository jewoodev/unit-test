package part2.chapter5.adapter.out.external.email;

import part2.chapter5.domain.Product;

public interface EmailGateway {
    void sendReceipt(String recipientEmail, Product product, int quantity);
}
