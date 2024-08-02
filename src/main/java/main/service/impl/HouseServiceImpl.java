package main.service.impl;

import main.dao.HouseDao;
import main.dao.impl.HouseDaoImpl;
import main.entity.House;
import main.service.HouseService;

import java.util.List;

public class HouseServiceImpl implements HouseService {
    private final HouseDao houseDao = new HouseDaoImpl();
    @Override
    public String save(House house) {
        return houseDao.save(house);
    }

    @Override
    public House findById(Long id) {
        return houseDao.findById(id);
    }

    @Override
    public String update(Long id, House house) {
        try {
            findById(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return houseDao.update(id, house);
    }

    @Override
    public List<House> getAll() {
        return houseDao.getAll();
    }

    @Override
    public String delete(Long id) {
        try {
            findById(id);
        } catch (Exception e) {
           return e.getMessage();
        }
        return houseDao.delete(id);
    }
}
