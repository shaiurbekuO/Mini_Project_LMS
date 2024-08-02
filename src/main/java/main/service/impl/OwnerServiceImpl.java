package main.service.impl;

import main.dao.OwnerDao;
import main.dao.impl.OwnerDaoImpl;
import main.entity.Owner;
import main.service.OwnerService;

import java.util.List;

public class OwnerServiceImpl implements OwnerService {
    private final OwnerDao ownerDao = new OwnerDaoImpl();
    @Override
    public String save(Owner owner) {
        return ownerDao.save(owner);
    }

    @Override
    public Owner findById(Long id) {
        return ownerDao.findById(id).orElseThrow(()-> new RuntimeException("Owner with id: "+id+"not found!"));

    }

    @Override
    public List<Owner> findAll() {
        return ownerDao.findAll();
    }

    @Override
    public String update(Long id, Owner owner) {
        try {
            findById(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return ownerDao.update(id, owner);
    }

    @Override
    public String delete(Long id) {
        try {
            findById(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return ownerDao.delete(id);
    }

}
