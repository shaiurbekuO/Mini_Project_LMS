package main.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import main.config.DatabaseConfig;
import main.dao.AgencyDao;
import main.entity.Address;
import main.entity.Agency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgencyDaoImpl implements AgencyDao {
    private final EntityManagerFactory entityManagerFactory = DatabaseConfig.entityManagerFactory();

    @Override
    public String save(Agency agency) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(agency);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        }
        finally {
            entityManager.close();
        }
        return "Agency saved";
    }

    @Override
    public Optional<Agency> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Agency agency = null;
        try {
            entityManager.getTransaction().begin();
             agency = entityManager.find(Agency.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return Optional.ofNullable(agency);
    }

    @Override
    public String update(Long id, Agency agency) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency foundAgency = entityManager.find(Agency.class, id);
            foundAgency.setName(agency.getName());
            foundAgency.setPhoneNumber(agency.getPhoneNumber());
            foundAgency.setAddress(agency.getAddress());
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        }finally {
            entityManager.close();
        }
        return "Updated Agency";
    }

    @Override
    public List<Agency> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Agency> agencies  = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            agencies = entityManager.createQuery("select a from Agency a", Agency.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if(entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }
        return agencies;
    }

    @Override
    public String deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, id);
            entityManager.remove(agency);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if(entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        }
        return "Deleted Agency";
    }

    @Override
    public String assignAgencyToAddress(Long agencyId, Long addressId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
//            entityManager.createQuery("update from Agency a set a.address = :address where a.id = :id")
//                    .setParameter("address", addressId)
//                    .setParameter("id", agencyId).executeUpdate();
            Address address = entityManager.find(Address.class, addressId);
            Agency agency = entityManager.find(Agency.class, agencyId);
            agency.setAddress(address);
            entityManager.merge(agency);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        }finally {
            entityManager.close();
        }
        return "Assigned Agency to Address";
    }

    @Override
    public String assignAgencyAndAddress(Agency agency) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(agency);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        }
        finally {
            entityManager.close();
        }
        return "Agency to Address";
    }

    @Override
    public String delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, id);
            entityManager.remove(agency);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        }finally {
            entityManager.close();
        }
        return "";
    }
}
