package main.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import main.config.DatabaseConfig;
import main.dao.CustomerDao;
import main.entity.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {
    private final EntityManagerFactory entityManagerFactory = DatabaseConfig.entityManagerFactory();
    @Override
    public Customer saveCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            System.out.println("Successfully saved Customer");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public String saveCustomerAndRentInfo(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return  e.getMessage();
        }finally {
            entityManager.close();
        }
        return "";
    }

    @Override
    public Optional<Customer> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = null;
        try {
            entityManager.getTransaction().begin();
            customer = entityManager.find(Customer.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return Optional.ofNullable(customer);
    }

    @Override
    public String update(Long id, Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer newCustomer = entityManager.find(Customer.class, id);
            newCustomer.setFirstName(customer.getFirstName());
            newCustomer.setLastName(customer.getLastName());
            newCustomer.setEmail(customer.getEmail());
            newCustomer.setBirthOfDate(customer.getBirthOfDate());
            newCustomer.setBirthDate(customer.getBirthDate());
            newCustomer.setGender(customer.getGender());
            newCustomer.setNationlity(customer.getNationlity());
            newCustomer.setFamilyStatus(customer.getFamilyStatus());
            newCustomer.setRentInfo(customer.getRentInfo());
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        }finally {
            entityManager.close();
        }
        return "Customer with id " + id + " has been updated";
    }

    @Override
    public List<Customer> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Customer> customers = null;
        try {
            entityManager.getTransaction().begin();
            customers = entityManager.createQuery("select c from Customer c").getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if(entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return customers;
    }

    @Override
    public String delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Customer.class, id));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        }finally {
            entityManager.close();
        }
        
        return "delete Customer with id " + id + " has been deleted";
    }
}
