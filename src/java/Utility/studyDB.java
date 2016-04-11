/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import JavaBeans.answer;
import JavaBeans.study;
import JavaBeans.user;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nitin
 */
public class studyDB {

    public static void addStudty(study Study) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(Study);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void updateStudy(study Study) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(Study);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static study getStudy(String SCode) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT s FROM study s "
                + "WHERE s.SCode = :SCode";
        TypedQuery<study> q = em.createQuery(qString, study.class);
        q.setParameter("SCode", SCode);
        try {
            study Study = q.getSingleResult();
            return Study;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    

    public static List<study> getStudies(String criteria) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT s FROM study s WHERE s.SStatus LIKE :status ";
        TypedQuery<study> q = em.createQuery(qString, study.class);
        if (criteria.equals("Open")) {
            q.setParameter("status", "Start");
        }else if(criteria.equals("Close"))
            q.setParameter("status", "Stop");
        else if(criteria.equals("All"))
            q.setParameter("status", "St%");
        try {
            List<study> Study = q.getResultList();
            if(Study.size()==0)
                return null;
            return Study;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static List<study> getStudiesFor(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT s FROM study s WHERE s.Email= :email";

        TypedQuery<study> q = em.createQuery(qString, study.class);
        q.setParameter("email", email);
        try {
            List<study> Study = q.getResultList();
            if(Study.size()==0)
                return null;
            return Study;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

}
