package jpabasic.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpabasic.domain.User;
import jpabasic.jpa.EMF;

public class RemoveUserService {
  public void removeUser(String email) {
    EntityManager em = EMF.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      User user = em.find(User.class, email);
      if (user == null) {
        throw new NoUserException();
      }
      em.remove(user);
      tx.commit();
    } catch(Exception ex) {
      tx.rollback();
      throw ex;
    } finally {
      em.close();
    }
  }
}