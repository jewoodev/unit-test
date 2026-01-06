package part2.chatper5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import part2.chapter5.Customer;
import part2.chapter5.Product;
import part2.chapter5.Store;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerTest {
    @Mock
    private Store storeMock;

    @Test
    public void testHasEnoughInventory() {
        when(storeMock.hasEnoughInventory(
                eq(Product.SHAMPOO),
                eq(5)
        )).thenReturn(false);

        var sut = new Customer(storeMock);

        boolean result = sut.purchase(Product.SHAMPOO, 5);

        assertThat(result).isFalse();
        verify(storeMock, times(0)).removeInventory(
                eq(Product.SHAMPOO),
                eq(5)
        );
    }
}