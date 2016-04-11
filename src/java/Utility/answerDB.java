/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import JavaBeans.answer;
import JavaBeans.study;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nitin
 */
public class answerDB {

    public static List<answer> getAnswerFor(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT a FROM answer a WHERE a.Email = :email";

        TypedQuery<answer> q = em.createQuery(qString, answer.class);
        q.setParameter("email", email);
        try {
            List<answer> Study = q.getResultList();
            if (Study.size() == 0) {
                return null;
            }
            return Study;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<answer> getAnswers(String SCode) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT a FROM answer a WHERE a.SCode= :SCode";

        TypedQuery<answer> q = em.createQuery(qString, answer.class);
        q.setParameter("Scode", SCode);
        try {
            List<answer> Study = q.getResultList();
            if (Study.size() == 0) {
                return null;
            }
            return Study;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static void addAnswer(answer Answer) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(Answer);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void updateAnswer(answer Answer) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(Answer);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static answer checkParticipants(String email, String SCode) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT a FROM answer a WHERE a.Email = :email AND a.SCode= :SCode";

        TypedQuery<answer> q = em.createQuery(qString, answer.class);
        q.setParameter("email", email);
        q.setParameter("SCode", SCode);
        try {
            answer Answer = q.getSingleResult();
            if (Answer == null) {
                return null;
            }
            return Answer;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
