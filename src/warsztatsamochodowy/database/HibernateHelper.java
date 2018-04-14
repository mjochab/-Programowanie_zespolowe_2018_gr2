/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.database;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import warsztatsamochodowy.database.entity.Klient;
import warsztatsamochodowy.database.entity.Samochod;

/**
 *
 * @author Piotr Świder
 */
public class HibernateHelper {

    private static SessionFactory sessionFactory;
    
    private static HibernateHelper instance;

    private HibernateHelper() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    
    public static HibernateHelper getInstance() {
        if(instance == null) {
            instance = new HibernateHelper();
        }
        return instance;
    }
    
     public static void closeHelper(){
         if(instance != null && sessionFactory.isOpen()) {
           sessionFactory.close();
         }
    }
     
    public List<Klient> getKlienci() {
        List<Klient> klienci = null;
        try (Session session = sessionFactory.openSession()) {
            Query q = session.createQuery("select k from Klient k order by k.id asc", Klient.class);
            klienci = q.getResultList();
            session.close();
        }
        if (klienci == null) {
            return new ArrayList<>();
        }
        return klienci;
    }

    public List<Samochod> getCars() {
        List<Samochod> cars = null;
        try (Session session = sessionFactory.openSession()) {
            Query q = session.createQuery("select s from Samochod s order by s.id asc", Samochod.class);
            cars = q.getResultList();
            session.close();
        }
        if (cars == null) {
            return new ArrayList<>();
        }
        return cars;
    }
  
    public Klient getKlientById(Long id) {
        Klient klient = null;
        try (Session session = sessionFactory.openSession()) {
            klient = session.find(Klient.class, id);
            session.close();
        }
        return klient;
    }
    
    public Samochod getCarById(Long id) {
        Samochod car = null;
        try (Session session = sessionFactory.openSession()) {
            car = session.find(Samochod.class, id);
            session.close();
        }
        return car;
    }

    private Object addOrUpdateEntity(Object entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(entity);
            tx.commit();
            session.close();
        }
        return entity;
    }

    public Klient addOrUpdateKlient(Klient klient) {
        return (Klient) addOrUpdateEntity(klient);
    }

    public Samochod addOrUpdateCar(Samochod car) {
        return (Samochod) addOrUpdateEntity(car);
    }

    public boolean deleteKlient(Long id) {
        boolean result = false;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("delete from Klient k where k.id = :id");
            q.setParameter("id", id);
            int res = q.executeUpdate();
            if (res == 1) {
                result = true;
            }
            tx.commit();
            session.close();
        }
        return result;
    }

    public boolean deleteCar(Long id) {
        boolean result = false;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("delete from Samochod s where s.id = :id");
            q.setParameter("id", id);
            int res = q.executeUpdate();
            if (res == 1) {
                result = true;
            }
            tx.commit();
            session.close();
        }
        return result;
    }
}
