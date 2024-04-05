package com.app.customer.service;

import com.app.customer.pojo.Customer;
import com.app.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer createCstomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(String id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        customer.setId(id);
        if (existingCustomer != null) {
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setCity(customer.getCity());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setFirstname(customer.getFirstname());
            existingCustomer.setLastname(customer.getLastname());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setState(customer.getState());
            existingCustomer.setStreet(customer.getStreet());
            return customerRepository.save(customer);
        } else {
            return null;
        }
    }

    public Customer findById(String id) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            return existingCustomer;
        } else {
            return null;
        }
    }

    public String deleteCustomer(String id) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            customerRepository.deleteById(id);
            return "Customer deleted successfully";
        }
        else {
            return "Customer not found";
        }
    }

    public Page<Customer> findAll(Pageable paging) {
        return customerRepository.findAll(paging);
    }

    public Page<Customer> findByFirstName(String firstName, Pageable paging) {
        return customerRepository.findByFirstname(firstName, paging);
    }

    public Page<Customer> findByLatName(String lastName, Pageable paging) {
        return customerRepository.findByLastname(lastName, paging);
    }

    public Page<Customer> findByCity(String city, Pageable paging) {
        return customerRepository.findByCity(city, paging);
    }

    public Page<Customer> findByEmail(String email, Pageable paging) {
        return customerRepository.findByEmail(email, paging);
    }

    public Page<Customer> findByPhone(String phone, Pageable paging) {
        return customerRepository.findByPhone(phone, paging);
    }
}
