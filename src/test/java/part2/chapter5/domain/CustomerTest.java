package part2.chapter5.domain;

import org.junit.jupiter.api.Test;
import part2.MockTestSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CustomerTest extends MockTestSupport {
    @Test
    public void purchaseWithLackingInventory() {
        // given
        when(super.store.hasEnoughInventory(
                eq(Product.SHAMPOO),
                eq(5)
        )).thenReturn(false);

        var sut = new Customer(super.store, "test@test.com");

        // when
        boolean result = sut.purchase(Product.SHAMPOO, 5);

        // then
        assertThat(result).isFalse();
        verify(super.store, times(0)).removeInventory(
                eq(Product.SHAMPOO),
                eq(5)
        );
    }

    @Test
    void purchaseWithEnoughInventory() {
        // given
        when(super.store.hasEnoughInventory(
                eq(Product.SHAMPOO),
                eq(5)
        )).thenReturn(true);

        var customer = new Customer(super.store, "test@test.com");

        boolean result = customer.purchase(Product.SHAMPOO, 5);
        assertThat(result).isTrue();
        verify(super.store).removeInventory(eq(Product.SHAMPOO), eq(5));
    }
}