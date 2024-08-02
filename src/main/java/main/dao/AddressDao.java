package main.dao;

import main.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDao {
    Optional<Address>  findById(Long id);

    String update(Long id, Address address);

    List<Address> findAll();

    String deleteById(Long id);

    List<Address> AddressOzuMNAgencyAlachsyn();


    List<Address> AddressCityAgency(String name);

    Address AddressAndAgency(Long id);
}
