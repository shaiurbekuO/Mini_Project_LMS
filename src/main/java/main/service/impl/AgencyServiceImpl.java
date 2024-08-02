package main.service.impl;

import main.dao.AddressDao;
import main.dao.AgencyDao;
import main.dao.impl.AddressDaoImpl;
import main.dao.impl.AgencyDaoImpl;
import main.entity.Address;
import main.entity.Agency;
import main.entity.RentInfo;
import main.service.AgencyService;

import java.util.List;
import java.util.Optional;

public class AgencyServiceImpl implements AgencyService {
    private final AgencyDao agencyDao = new AgencyDaoImpl();


    @Override
    public String save(Agency agency) {
        return agencyDao.save(agency);
    }

    @Override
    public Agency findById(Long id) {
        return agencyDao.findById(id).orElseThrow(()-> new RuntimeException("Agency with id: "+id+"not found!"));
    }

    @Override
    public String update(Long id, Agency agency) {
        try {
            findById(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return agencyDao.update(id, agency);
    }

    @Override
    public List<Agency> findAll() {
        return agencyDao.findAll();
    }

    @Override
    public String deleteById(Long id) {
        try {
            findById(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return agencyDao.deleteById(id);
    }

    @Override
    public String assignAgencyToAddress(Long agencyId, Long addressId) {
        try {
            findById(agencyId);
        } catch (Exception e) {return e.getMessage();
        }
        return agencyDao.assignAgencyToAddress(agencyId, addressId);
    }

    @Override
    public String assignAgencyAndAddress(Agency agency) {
        return agencyDao.assignAgencyAndAddress(agency);
    }

    @Override
    public String deleteAgencyAndAddressAndRentInfo(Long id) {
        try {
            findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return agencyDao.delete(id);
    }


}
