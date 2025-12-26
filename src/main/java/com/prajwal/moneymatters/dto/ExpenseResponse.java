package com.prajwal.moneymatters.dto;

import com.prajwal.moneymatters.Model.Expense;

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

    public ExpenseResponse(Expense expense){
        this.id = expense.getId();
        this.title = expense.getTitle();
        this.category = expense.getCategory();
        this.amount = expense.getAmount();
        this.expenseDate = expense.getExpenseDate();
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
