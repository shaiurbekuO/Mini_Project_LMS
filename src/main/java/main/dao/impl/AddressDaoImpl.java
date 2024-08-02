package main.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import main.config.DatabaseConfig;
import main.dao.AddressDao;
import main.entity.Address;
import main.entity.Agency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressDaoImpl implements AddressDao {
    private final EntityManagerFactory entityManagerFactory = DatabaseConfig.entityManagerFactory();


    @Override
    public Optional<Address> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Address address = null;
        try{
            entityManager.getTransaction().begin();
            address = entityManager.find(Address.class, id);
            entityManager.getTransaction().commit();
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return Optional.ofNullable(address);
    }

    @Override
    public String update(Long id, Address address) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Address foundAddress = entityManager.find(Address.class, id);
            foundAddress.setCity(address.getCity());
            foundAddress.setRegion(address.getRegion());
            foundAddress.setStreet(address.getStreet());
            entityManager.getTransaction().commit();
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        }finally {
            entityManager.close();
        }
        return "Address updated successfully";
    }

    @Override
    public List<Address> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Address> addresses = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
             addresses = entityManager.createQuery("select a from Address a", Address.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if(entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }
        return addresses;
    }

    @Override
    public String deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Address address = entityManager.find(Address.class, id);
            entityManager.remove(address);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        }finally {
            entityManager.close();
        }
        return "Deleted address successfully";
    }

    @Override
    public List<Address> AddressOzuMNAgencyAlachsyn() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Address> addresses = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            addresses = entityManager.createQuery("SELECT DISTINCT a FROM Address a LEFT JOIN FETCH a.agency", Address.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return addresses;
    }

    @Override
    public List<Address> AddressCityAgency(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Address> addresses = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
//            addresses = (List<Address>) entityManager.createQuery("select a from Agency a inner join Address  ad where  ad.city = :city", Address.class)
//                    .getResultStream().distinct();
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return addresses;
    }

    @Override
    public Address AddressAndAgency(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Address address = entityManager.find(Address.class, id);
            
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }
        return null;
    }


}
