package part2.chapter9.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import part2.chapter9.domain.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Optional<Company> findByCompanyId(Integer companyId);
}
