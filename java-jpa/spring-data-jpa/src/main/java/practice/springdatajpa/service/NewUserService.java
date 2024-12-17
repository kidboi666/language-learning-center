package practice.springdatajpa.service;

import jakarta.persistence.Entity;
import java.util.Optional;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.transaction.annotation.Transactional;
import practice.springdatajpa.entity.User;
import practice.springdatajpa.repository.UserRepository;

@Entity
public class NewUserService {

  private UserRepository userRepository;

  public NewUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public void saveUser(SaveRequest saveRequest) {
    Optional<User> userOpt = userRepository.findById(saveRequest.getEmail());

  }
}
