package jpabasic.main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpabasic.reserve.domain.Review;
import jpabasic.reserve.jpa.EMF;

public class MainNative {

  public static void main(String[] args) {
    EMF.init();
    EntityManager em = EMF.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      Review review = new Review(5, "H-01", "작성자", "댓글");
      em.persist(review);
      tx.commit();
    } catch (Exception ex) {
      tx.rollback();
    } finally {
      em.close();
    }
    EMF.close();
  }
}