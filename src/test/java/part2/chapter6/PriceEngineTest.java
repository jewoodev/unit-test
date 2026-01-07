package part2.chapter6;

import org.junit.jupiter.api.Test;
import part2.chapter5.domain.Product;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PriceEngineTest {
    @Test
    void discountOfTwoProducts() {
        var product1 = Product.SHAMPOO;
        var product2 = Product.HAND_WASH;
        var sut = new PriceEngine();

        BigDecimal discount = sut.calculateDiscount(List.of(product1, product2));

        assertThat(BigDecimal.valueOf(0.02)).isEqualByComparingTo(discount);
    }
}