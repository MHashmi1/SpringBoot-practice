package com.SpringBoot.Template.controller;

import com.SpringBoot.Template.model.Customer;
import com.SpringBoot.Template.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository){
        this.repository = repository;
    }

    @PostMapping("/create")
    public Customer create(@RequestBody Customer customer) {
        return this.repository.save(customer);
    }

    //localhost:8080/list
    @GetMapping("/list")
    public Iterable<Customer> list() {
        return this.repository.findAll();
    }

    //localhost:8080/read/lastname/Doe
    @GetMapping("/read/lastname/{lastName}")
    public Iterable<Customer> read(@PathVariable String lastName) {
        return this.repository.findByLastName(lastName);
    }


    @DeleteMapping("/delete")
    public void delete(@RequestBody Customer customer) {
        this.repository.delete(customer);
    }

    @PatchMapping("/update/id/{id}")
    public void patchUpdate(@RequestBody Customer customerInput,@PathVariable Long id) {
        Customer customer = this.repository.findById(id).get();
        customer.setFirstName(customerInput.getFirstName());
        customer.setLastName(customerInput.getLastName());
        this.repository.save(customer);
    }

    @PutMapping("/update/id/{id}")
    public void putUpdate(@RequestBody Customer customerInput,@PathVariable Long id) {
        Customer customer = this.repository.findById(id).get();
        customer.setFirstName(customerInput.getFirstName());
        customer.setLastName(customerInput.getLastName());
        this.repository.save(customer);
    }

}
