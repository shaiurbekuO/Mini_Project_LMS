package main.service;

import main.entity.Owner;

import java.util.List;

public interface OwnerService {
    String save(Owner owner);

    Owner findById(Long id);


    List<Owner> findAll();

    String update(Long id, Owner owner);

    String delete(Long id);
}
