package main.service.impl;

import main.dao.AddressDao;
import main.dao.impl.AddressDaoImpl;
import main.entity.Address;
import main.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    private final AddressDao addressDao = new AddressDaoImpl();



    @Override
    public Address findById(Long id) {
        return addressDao.findById(id).orElseThrow(() -> new RuntimeException("Address with id: "+id+"not found!"));
    }

    @Override
    public String update(Long id, Address address) {
        try {
            findById(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return addressDao.update(id, address);
    }

    @Override
    public List<Address> findAll() {
        return addressDao.findAll();
    }

    @Override
    public String deleteById(Long id) {
        try {
            findById(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return addressDao.deleteById(id);
    }

    @Override
    public List<Address> AddressOzuMNAgencyAlachsyn() {
        return addressDao.AddressOzuMNAgencyAlachsyn();
    }

    @Override
    public List<Address> AddressCityAgency(String name) {
        return addressDao.AddressCityAgency(name);
    }

    @Override
    public Address AddressAndAgency(Long id) {
        return addressDao.AddressAndAgency(id);
    }

}
