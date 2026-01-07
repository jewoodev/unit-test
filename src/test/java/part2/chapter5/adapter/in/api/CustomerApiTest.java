package part2.chapter5.adapter.in.api;

import org.junit.jupiter.api.Test;
import part2.MockTestSupport;
import part2.chapter5.domain.Customer;
import part2.chapter5.domain.Product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerApiTest extends MockTestSupport {
    @Test
    void purchaseSuccessfully() {
        // given
        var sut = new CustomerApi(super.customerRepository, super.productRepository, super.emailGateway);

        when(super.store.hasEnoughInventory(eq(Product.SHAMPOO), eq(1))).thenReturn(true);
        when(super.customerRepository.findById(eq(1))).thenReturn(new Customer(store, "John Doe"));
        when(super.productRepository.findById(eq(1))).thenReturn(Product.SHAMPOO);

        // when
        boolean isSuccess = sut.purchase(1, 1, 1);

        // then
        assertThat(isSuccess).isTrue();
        verify(super.emailGateway).sendReceipt(eq("John Doe"), eq(Product.SHAMPOO), eq(1));
    }
}