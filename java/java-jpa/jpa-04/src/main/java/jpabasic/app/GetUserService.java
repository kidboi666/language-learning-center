package jpabasic.app;

import jakarta.persistence.EntityManager;
import jpabasic.domain.User;
import jpabasic.jpa.EMF;

public class GetUserService {
  public User getUser(String email) {
    EntityManager em = EMF.createEntityManager();
    try {
      User user = em.find(User.class, email);
      if (user == null) {
        throw new NoUserException();
      }
      return user;
    } finally {
      em.close();
    }
  }
}