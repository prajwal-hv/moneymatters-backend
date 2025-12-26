package com.prajwal.moneymatters.service;

import com.prajwal.moneymatters.dto.CreateExpenseRequest;
import com.prajwal.moneymatters.dto.ExpenseResponse;
import com.prajwal.moneymatters.dto.MonthlyExpenseSummaryResponse;
import com.prajwal.moneymatters.dto.UpdateExpenseRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExpenseService {

    ExpenseResponse create(CreateExpenseRequest request);

    Page<ExpenseResponse> getMyExpenses(Pageable pageable);


    ExpenseResponse update(Long expenseId, UpdateExpenseRequest request);

    void delete(Long expenseId);

    MonthlyExpenseSummaryResponse getMonthlySummary(int year, int month);
}
