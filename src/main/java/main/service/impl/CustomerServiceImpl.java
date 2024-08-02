package main.service.impl;

import main.dao.CustomerDao;
import main.dao.impl.CustomerDaoImpl;
import main.entity.Customer;
import main.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerDao.saveCustomer(customer);
    }

    @Override
    public String saveCustomerAndRentInfo(Customer customer) {
        return customerDao.saveCustomerAndRentInfo(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerDao.findById(id).orElseThrow(()-> new RuntimeException("Customer with id: "+id+"not found!"));
    }

    @Override
    public String update(Long id, Customer customer) {
        try {
            findById(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return customerDao.update(id, customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public String delete(Long id) {
        try {
            findById(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return customerDao.delete(id);
    }
}
