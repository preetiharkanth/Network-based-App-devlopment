/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import JavaBeans.tempuser;
import JavaBeans.user;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Preeti Harkanth
 */
public class TempUserDB {
    
    public static tempuser getUser(String token,String email) {
              EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT t FROM tempuser t "
                + "WHERE t.tempuserEmail = :email AND t.Token=:token";
        TypedQuery<tempuser> q = em.createQuery(qString, tempuser.class);
        q.setParameter("email", email);
        q.setParameter("token", token);
        try {
            tempuser User = q.getSingleResult();
            
            return User;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
        
    }
            
    public static void addTempUser(tempuser tUser){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(tUser);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
        
    }
    
public static void delete(tempuser tuser) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(tuser));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }
    
}
