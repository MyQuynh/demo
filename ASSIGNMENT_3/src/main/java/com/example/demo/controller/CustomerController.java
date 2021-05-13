package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerUI;
import com.example.demo.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {
    @Autowired
    CustomerRepository repository;

    @GetMapping("/bulkcreate")
    public String bulkcreate(){
        // save a single Customer
        repository.save(new Customer("Rajesh", "Bhojwani"));

        // save a list of Customers
        repository.saveAll(Arrays.asList(new Customer("Salim", "Khan")
                , new Customer("Rajesh", "Parihar")
                , new Customer("Rahul", "Dravid")
                , new Customer("Dharmendra", "Bhojwani")));

        return "Customers are created";
    }
    @PostMapping("/create")
    public String create(@RequestBody CustomerUI customer){
        // save a single Customer
        repository.save(new Customer(customer.getFirstName(), customer.getLastName()));

        return "Customer is created";
    }
    @GetMapping("/findall")
    public List<CustomerUI> findAll(){

        List<Customer> customers = repository.findAll();
        List<CustomerUI> customerUI = new ArrayList<>();

        for (Customer customer : customers) {
            customerUI.add(new CustomerUI(customer.getFirstName(),customer.getLastName()));
        }

        return customerUI;
    }

    @RequestMapping("/search/{id}")
    public String search(@PathVariable long id){
        String customer = "";
        customer = repository.findById(id).toString();
        return customer;
    }

    @RequestMapping("/searchbyfirstname/{firstname}")
    public List<CustomerUI> fetchDataByLastName(@PathVariable String firstname){

        List<Customer> customers = repository.findByFirstName(firstname);
        List<CustomerUI> customerUI = new ArrayList<>();

        for (Customer customer : customers) {
            customerUI.add(new CustomerUI(customer.getFirstName(),customer.getLastName()));
        }

        return customerUI;
    }
}