package main.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import main.config.DatabaseConfig;
import main.dao.HouseDao;
import main.entity.House;
import main.entity.Owner;

import java.util.ArrayList;
import java.util.List;

public class HouseDaoImpl implements HouseDao {
    private final EntityManagerFactory entityManagerFactory = DatabaseConfig.entityManagerFactory();
    @Override
    public String save(House house) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(house);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            rollback(entityManager);
            return e.getMessage();
        }
        return "Save Success";
    }

    @Override
    public House findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        House house = null;
        try {
            entityManager.getTransaction().begin();
            house = entityManager.find(House.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            rollback(entityManager);
            System.err.println(e.getMessage());
        }

        return house;
    }

    @Override
    public String update(Long id, House house) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            House newHouse = entityManager.find(House.class, id);
            entityManager.getTransaction().begin();
            newHouse.setHouseType(house.getHouseType());
            newHouse.setPrice(house.getPrice());
            newHouse.setRating(house.getRating());
            newHouse.setRoom(house.getRoom());
            newHouse.setFurniture(house.isFurniture());
            newHouse.setDescription(house.getDescription());
            newHouse.setOwner(house.getOwner());
            newHouse.setAddress(house.getAddress());
            newHouse.setRentInfo(house.getRentInfo());
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            rollback(entityManager);
            return e.getMessage();
        }
        return "update success";
    }

    @Override
    public List<House> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House> houses = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h", House.class).getResultList();
        } catch (Exception e) {
            rollback(entityManager);
            System.err.println(e.getMessage());
        }
        return houses;
    }
    private void rollback(EntityManager entityManager) {
        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().rollback();
        entityManager.close();
    }

    @Override
    public String delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House house = entityManager.find(House.class, id);
            entityManager.remove(house);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            rollback(entityManager);
            return e.getMessage();
        }
        return "Delete successful";
    }
}
