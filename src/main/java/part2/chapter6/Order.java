package part2.chapter6;

import part2.chapter5.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Product> innerProducts;
    public List<Product> products;

    public Order() {
        this.innerProducts = new ArrayList<>();
        this.products = innerProducts;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}
