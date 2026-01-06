package part2.chapter5.adapter.out.persistence;

import part2.chapter5.domain.Product;

public interface ProductRepository {
    Product findById(int id);
}
