package main.service;

import main.entity.Address;

import java.util.List;

public interface AddressService {

    Address findById(Long id);

    String update(Long id, Address address);

    List<Address> findAll();

    String deleteById(Long id);

    List<Address> AddressOzuMNAgencyAlachsyn();


    List<Address> AddressCityAgency(String name);

    Address AddressAndAgency(Long id);
}
