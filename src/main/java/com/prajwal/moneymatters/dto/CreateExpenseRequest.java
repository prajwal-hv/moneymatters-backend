package com.prajwal.moneymatters.dto;

import com.prajwal.moneymatters.Model.ExpenseCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateExpenseRequest {

    @NotBlank
    private String title;

    @Positive
    private BigDecimal amount;

    @NotNull
    private ExpenseCategory category;

    @NotNull
    private LocalDate date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public @NotNull ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(@NotNull ExpenseCategory category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CreateExpenseRequest{" +
                "title='" + title + '\'' +
                ", amount=" + amount +
                ", category=" + category +
                ", date=" + date +
                '}';
    }
}
