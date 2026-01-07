package part2.chapter5.application.required;

import part2.chapter5.domain.Product;

public interface ProductRepository {
    Product findById(int id);
}
