package com.prajwal.moneymatters.dto;

import java.math.BigDecimal;
import java.util.Map;

public class MonthlyExpenseSummaryResponse {

    private int year;
    private int month;
    private BigDecimal totalAmount;
    private Map<String, BigDecimal> byCategory;

    public MonthlyExpenseSummaryResponse(int year, int month, BigDecimal totalAmount, Map<String, BigDecimal> byCategory){
        this.year = year;
        this.month = month;
        this.totalAmount = totalAmount;
        this.byCategory =  byCategory;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Map<String, BigDecimal> getByCategory() {
        return byCategory;
    }
}
