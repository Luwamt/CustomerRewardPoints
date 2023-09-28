package com.customerawardpoints.service;

import com.customerawardpoints.model.Customer;
import com.customerawardpoints.model.CustomerThreeMonthData;

import java.util.List;
public interface CustomerService {

    public  Customer addNewCustomerReward(Customer rewardProgram);
    public CustomerThreeMonthData calculatePoints(Long customerId) ;
    public List<Customer> getAllCustomerRewards();
    public Customer getCustomerRewardProgramBy_Id(Long customerId);
}
