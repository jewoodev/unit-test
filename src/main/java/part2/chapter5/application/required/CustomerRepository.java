package part2.chapter5.application.required;

import part2.chapter5.domain.Customer;

public interface CustomerRepository {
    Customer findById(int id);
}
