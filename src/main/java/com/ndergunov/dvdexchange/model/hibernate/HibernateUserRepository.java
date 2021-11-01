package com.ndergunov.dvdexchange.model.hibernate;

import com.ndergunov.dvdexchange.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Repository
@Transactional
public class HibernateUserRepository {
    @Autowired
    SessionFactory sessionFactory;
    public User loadUserByUsername(String username) throws UsernameNotFoundException{
        Session session = this.sessionFactory.getCurrentSession();
        try {
            return (User) session.createQuery("from User as User where login=:username")
                .setParameter("username",username)
                .getSingleResult();
        }catch (NoResultException e){
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
    public User loadUserByUsernameAndPassword(String username, String password){
        User us = loadUserByUsername(username);
        if (us.getPassword().equals(password)) return us;
        else throw new UsernameNotFoundException("wrong password for user"+username);
    }
}
