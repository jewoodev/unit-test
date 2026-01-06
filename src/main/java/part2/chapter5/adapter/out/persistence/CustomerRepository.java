package part2.chapter5.adapter.out.persistence;

import part2.chapter5.domain.Customer;

public interface CustomerRepository {
    Customer findById(int id);
}
