package com.customerawardpoints.controller;

import com.customerawardpoints.model.Customer;
import com.customerawardpoints.model.CustomerThreeMonthData;
import com.customerawardpoints.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/customers"})
public class CustomerController {

@Autowired
private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<?> createCustomerRewardProgram(@RequestBody Customer customerRewards){
        return  new ResponseEntity<>( customerService.addNewCustomerReward(customerRewards), HttpStatus.CREATED);
    }
        @GetMapping("/")
        public ResponseEntity<List<Customer>> getAllCustomerRewardsProgram() {

            return  new ResponseEntity<>( customerService.getAllCustomerRewards(), HttpStatus.OK);
        }
    @GetMapping(value = {"/{customerId}"})
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Long customerId) {
        return new ResponseEntity<>(customerService.getCustomerRewardProgramBy_Id(customerId), HttpStatus.OK);
    }

        @GetMapping(value = {"/calculatePoints/{customerId}"})
        public ResponseEntity<CustomerThreeMonthData> getCustomerRewardsById(@PathVariable("customerId") Long customerId) {
            return new ResponseEntity<>(customerService.calculatePoints(customerId), HttpStatus.OK);
        }

}
