package part2.chapter8.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import part2.chapter8.domain.Company;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    List<Company> findByCompanyId(Integer companyId);
}
