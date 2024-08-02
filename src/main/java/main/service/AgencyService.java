package main.service;

import main.entity.Agency;

import java.util.List;

public interface AgencyService {
    String save(Agency agency);
    Agency findById(Long id);

    String update(Long id, Agency agency);

    List<Agency> findAll();

    String deleteById(Long id);

    String assignAgencyToAddress(Long agencyId, Long addressId);

    String assignAgencyAndAddress(Agency agency);

    String deleteAgencyAndAddressAndRentInfo(Long id);
}
