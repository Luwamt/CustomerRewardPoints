package com.customerawardpoints.service.Impl;

import com.customerawardpoints.model.Customer;
import com.customerawardpoints.model.CustomerThreeMonthData;
import com.customerawardpoints.repository.CustomerRepository;
import com.customerawardpoints.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer addNewCustomerReward(Customer customerRewards) {
        return customerRepository.save(customerRewards);
    }
    @Override
    public CustomerThreeMonthData calculatePoints(Long customerId) {
        // Retrieve customerTransactions by the customerId
        List<Customer> customerTransactions = customerRepository.findByCustomerId(customerId);

        // Calculate reward points for each transaction and group them by month
        Map<String, Integer> monthlyPoints = new HashMap<>();
        int totalPoints = 0;

        for (Customer customer : customerTransactions) {
            String month = customer.getDates().getMonth().toString();
            int points = calculatePoints(customer.getPurchases());

            monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);
            totalPoints += points;
        }

        CustomerThreeMonthData rewardPoints = new CustomerThreeMonthData();
        rewardPoints.setCustomerId(customerId);
        rewardPoints.setMonthlyPoints(monthlyPoints);
        rewardPoints.setTotalPoints(totalPoints);

        return rewardPoints;
    }

    private int calculatePoints(double purchaseAmount) {
        // Calculate reward points based on purchase amount
       if(purchaseAmount< 50){
            return 0;
       }else if(purchaseAmount <= 100){
           return (int) (purchaseAmount - 50);
    }
       else
           return (int)(purchaseAmount - 100) * 2 +50;
    }
    @Override
    public List<Customer> getAllCustomerRewards() {
        return customerRepository.findAll() ;
    }

    @Override
    public Customer getCustomerRewardProgramBy_Id(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(()-> new IllegalArgumentException("The CustomerId:  "+customerId+" is not found"));
    }
}
