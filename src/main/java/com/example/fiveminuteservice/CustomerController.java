package com.example.fiveminuteservice;

import com.example.fiveminuteservice.model.Customer;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CustomerController {

    private final CustomerService customerService;
    private final ObservationRegistry registry;

    public CustomerController(final CustomerService customerService, final ObservationRegistry registry) {
        this.customerService = customerService;
        this.registry = registry;
    }

    @GetMapping("/customers")
    public Collection<Customer> getAllCustomers(){
        return customerService.all();
    }


    @GetMapping("/customers/{name}")
    public Customer getCustomerByName(@PathVariable String name) {
        return Observation.createNotStarted("getCustomerByName", this.registry)
                .observe(() -> customerService.byName(name));
    }


}
