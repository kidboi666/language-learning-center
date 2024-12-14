package me.leejinwook.springbootdeveloper.repository;

import java.util.Optional;
import me.leejinwook.springbootdeveloper.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

  Optional<RefreshToken> findByUserId(Long userId);

  Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
