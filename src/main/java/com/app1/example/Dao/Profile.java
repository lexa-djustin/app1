package com.app1.example.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class Profile implements ProfileInterface {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void save(com.app1.example.Entity.Profile profile) {
        System.out.println(em == null);
        //Session session = sessionFactory.getCurrentSession();
        em.getTransaction().begin();
        em.persist(profile);
        em.flush();

        //session.save(profile);

        System.out.println("Save");
    }
}
