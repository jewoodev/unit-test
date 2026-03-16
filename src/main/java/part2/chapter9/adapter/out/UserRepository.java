package part2.chapter9.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import part2.chapter9.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(Long userId);
}
