package practice.springdatajpa.repository;

import java.util.Optional;
import org.springframework.data.repository.Repository;
import practice.springdatajpa.entity.User;

public interface UserRepository extends Repository<User, Long> {
  Optional<User> findById(Long id);

  void save(User user);

  void deleteById(Long id);

}
