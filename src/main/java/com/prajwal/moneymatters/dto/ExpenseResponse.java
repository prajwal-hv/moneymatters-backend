package com.prajwal.moneymatters.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prajwal.moneymatters.Model.Expense;
import com.prajwal.moneymatters.Model.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseResponse {

    private Long id;
    private String title;
    private BigDecimal amount;
    private ExpenseCategory category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    public ExpenseResponse(Long id, String title, ExpenseCategory category, BigDecimal amount, LocalDate date) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public ExpenseResponse(Expense expense){
        this.id = expense.getId();
        this.title = expense.getTitle();
        this.category = expense.getCategory();
        this.amount = expense.getAmount();
        this.date = expense.getExpenseDate();
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

    public ExpenseCategory getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }
}
