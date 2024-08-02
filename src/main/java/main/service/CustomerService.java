package main.service;

import main.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    String saveCustomerAndRentInfo(Customer customer);

    Customer findById(Long id);

    String update(Long id, Customer customer);

    List<Customer> getAll();

    String delete(Long id);
}
