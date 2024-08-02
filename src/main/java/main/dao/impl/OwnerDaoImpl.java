package main.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import main.config.DatabaseConfig;
import main.dao.OwnerDao;
import main.entity.Agency;
import main.entity.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OwnerDaoImpl implements OwnerDao {
    private final EntityManagerFactory entityManagerFactory = DatabaseConfig.entityManagerFactory();
    @Override
    public String save(Owner owner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(owner);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        }finally {
            entityManager.close();
        }
        return "Save successful";
    }

    @Override
    public Optional<Owner> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Owner owner = null;
        try {
            entityManager.getTransaction().begin();
            owner = entityManager.find(Owner.class, id);
            owner.getHouses().size();
            owner.getAgencies().size();
            owner.getRentInfos().size();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }finally {
            entityManager.close();
        }

        return Optional.ofNullable(owner);
    }
    @Override
    public List<Owner> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Owner> owners = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            owners = entityManager.createQuery("select o from Owner o", Owner.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if(entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return owners;
    }

    @Override
    public String update(Long id, Owner owner) {
        return "";
    }

    @Override
    public String delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, id);
            entityManager.remove(owner);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        }finally {
            entityManager.close();
        }
        return "Delete successful";
    }
}
