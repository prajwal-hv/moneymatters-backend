package com.prajwal.moneymatters.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseResponse {

    private Long id;
    private String title;
    private BigDecimal amount;
    private String category;
    private LocalDate expenseDate;

    public ExpenseResponse(Long id, String title, String category, BigDecimal amount, LocalDate expenseDate) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.expenseDate = expenseDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }
}
