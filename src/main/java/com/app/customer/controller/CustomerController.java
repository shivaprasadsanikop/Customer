package com.app.customer.controller;

import com.app.customer.pojo.Customer;
import com.app.customer.repository.CustomerRepository;
import com.app.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService service;

    @PostMapping("/addcustomer")
    public Customer addCustomer(@RequestBody Customer customer) {
        return service.createCstomer(customer);
    }

    @PostMapping("/updateCustomer/{id}")

    public Customer updateCustomer(@PathVariable  String id, @RequestBody Customer customer) {
        return service.updateCustomer(id, customer);
    }

    @GetMapping("/searchCustomerById/{id}")
    public Customer searchCustomerById(@PathVariable String id) {
        return service.findById(id);
    }

    @DeleteMapping("/deleteCustomer/{id}")

    public String deleteCustomer(@PathVariable String id) {
        return service.deleteCustomer(id);
    }

    @GetMapping("/customers")
    public ResponseEntity<Map<String, Object>> getAllCustomers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort
    ) {

        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            List<Customer> customers = new ArrayList<Customer>();
            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    Sort.Direction dire = _sort[1].contains("desc")?Sort.Direction.DESC:Sort.Direction.ASC;
                    Sort.Order order = new Sort.Order(dire,_sort[0]);
                    orders.add(order);
                }
            }

            Pageable paging = PageRequest.of(page, size, Sort.by(orders));

            Page<Customer> pageTuts;
            if(firstName != null)
                pageTuts = service.findByFirstName(firstName, paging);
            else if(lastName != null)
                pageTuts = service.findByLatName(lastName, paging);
            else if (city != null)
                pageTuts = service.findByCity(city, paging);
            else if (email != null)
                pageTuts = service.findByEmail(email, paging);
            else if (city != null)
                pageTuts = service.findByPhone(phone, paging);
            else
                pageTuts = service.findAll(paging);
            customers = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("customers", customers);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
