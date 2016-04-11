/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import JavaBeans.recommend;
import JavaBeans.tempuser;
import JavaBeans.user;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nitin
 */
public class userDB {

    public static void update(user User) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(User);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static user getUser(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM user u "
                + "WHERE u.Email = :email";
        TypedQuery<user> q = em.createQuery(qString, user.class);
        q.setParameter("email", email);
        try {
            user User = q.getSingleResult();
            return User;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static user validateUser(String email, String password) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM user u "
                + "WHERE u.Email = :email";
        TypedQuery<user> q = em.createQuery(qString, user.class);
        q.setParameter("email", email);
        boolean flag = false;
        try {
            user User = q.getSingleResult();
            try {
                flag = generatePass.validatePassword(password, User.getPassword());
            } catch (Exception e) {
            }

            if ((User.getEmail()).equals(email) && flag) {

                return User;
            } else {
                return null;
            }
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static boolean emailExists(String email) {
        user u = getUser(email);
        return u != null;
    }

    public static void addUser(user User) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(User);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void radd(recommend rUser) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(rUser);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static recommend getRUser(String newEmail) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT r FROM recommend r "
                + "WHERE r.NewUserEmail=:newEmail";
        TypedQuery<recommend> q = em.createQuery(qString, recommend.class);
        q.setParameter("newEmail", newEmail);
        try {
            recommend RUser = q.getSingleResult();

            return RUser;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }

    }

    public static void rdelete(recommend User) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(User));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
