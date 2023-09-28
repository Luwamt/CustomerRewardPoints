package com.customerawardpoints.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Component
public class CustomerThreeMonthData {
    private Long customerId;
    private Map<String, Integer> monthlyPoints;
    private int TotalPoints;


}