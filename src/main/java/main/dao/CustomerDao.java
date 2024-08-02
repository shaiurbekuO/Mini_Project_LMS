package main.dao;

import main.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    Customer saveCustomer(Customer customer);

    String saveCustomerAndRentInfo(Customer customer);

    Optional<Customer> findById(Long id);

    String update(Long id, Customer customer);

    List<Customer> getAll();

    String delete(Long id);
}
