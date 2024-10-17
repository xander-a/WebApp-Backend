package com.xander;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
// @CrossOrigin(origins = "http://localhost:3000") // Replace with React app URL
@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
@CrossOrigin
public class customerMain {

    private final CustomerRepository customerRepository;

    public customerMain(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args){
        SpringApplication.run(customerMain.class, args);

    }

    // Retrieve data from the database
    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
        String name,
        String email,
        Integer age)
    {


    }

    record UpdateCustomerRequest(
            String name,
            String email,
            Integer age
    ){

    }
// Input values into the database
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
    Customer customer = new Customer();
    customer.setName(request.name());
    customer.setEmail(request.email());
    customer.setAge(request.age());
    customerRepository.save(customer);
    }

    // Delete values from the database
    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    //Update data from the database
    @PutMapping ("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody UpdateCustomerRequest customerDetails){
        Customer updateCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: " + id));
        updateCustomer.setName(customerDetails.name());
        updateCustomer.setEmail(customerDetails.email());
        updateCustomer.setAge(customerDetails.age());
        customerRepository.save(updateCustomer);
    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
