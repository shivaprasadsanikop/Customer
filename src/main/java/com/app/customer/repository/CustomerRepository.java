package com.app.customer.repository;

import com.app.customer.pojo.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, String>, CrudRepository<Customer, String> {


    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findByFirstname(String  firstname, Pageable pageable);
    Page<Customer> findByLastname(String  lastname, Pageable pageable);
    Page<Customer> findByCity(String  city, Pageable pageable);
    Page<Customer> findByEmail(String  email, Pageable pageable);
    Page<Customer> findByPhone(String  phone, Pageable pageable);

}
