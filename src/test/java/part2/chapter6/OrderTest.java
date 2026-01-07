package part2.chapter6;

import org.junit.jupiter.api.Test;
import part2.chapter5.domain.Product;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    @Test
    void addingAProductToAnOrder() {
        var product = Product.HAND_WASH;
        var sut = new Order();

        sut.addProduct(product);

        assertThat(sut.products.size()).isEqualTo(1);
        assertThat(sut.products.get(0)).isEqualTo(product);
    }
}