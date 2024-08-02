package main.dao;

import main.entity.House;

import java.util.List;

public interface HouseDao {
    String save(House house);

    House findById(Long id);

    String update(Long id, House house);

    List<House> getAll();

    String delete(Long id);
}
