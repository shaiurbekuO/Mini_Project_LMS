package main.dao;

import main.entity.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyDao {
    String save(Agency agency);
    Optional<Agency> findById(Long id);

    String update(Long id, Agency agency);

    List<Agency> findAll();

    String deleteById(Long id);

    String assignAgencyToAddress(Long agencyId, Long addressId);

    String assignAgencyAndAddress(Agency agency);

    String delete(Long id);
}
