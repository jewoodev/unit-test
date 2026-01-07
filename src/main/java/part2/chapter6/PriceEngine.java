package part2.chapter6;

import part2.chapter5.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public class PriceEngine {
    public BigDecimal calculateDiscount(List<Product> products) {
        BigDecimal discount = BigDecimal.valueOf(0.01).multiply(BigDecimal.valueOf(products.size()));
        return discount.min(BigDecimal.valueOf(0.2));
    }
}
