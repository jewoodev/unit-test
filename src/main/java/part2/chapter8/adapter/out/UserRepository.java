package part2.chapter8.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import part2.chapter8.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserId(Long userId);
}
