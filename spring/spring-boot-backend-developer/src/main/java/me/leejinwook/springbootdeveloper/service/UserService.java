package me.leejinwook.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.leejinwook.springbootdeveloper.domain.User;
import me.leejinwook.springbootdeveloper.dto.AddUserRequest;
import me.leejinwook.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Long save(AddUserRequest dto) {
    return userRepository.save(User.builder()
        .email(dto.getEmail())
        // 패스워드 암호화
        .password(bCryptPasswordEncoder.encode(dto.getPassword())).build()).getId();
  }

  public User findById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
  }
  
}
