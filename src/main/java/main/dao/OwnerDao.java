package main.dao;

import main.entity.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerDao {
    String save(Owner owner);

    Optional<Owner> findById(Long id);

    List<Owner> findAll();

    String update(Long id, Owner owner);

    String delete(Long id);
}
