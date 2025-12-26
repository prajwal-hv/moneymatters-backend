package com.prajwal.moneymatters.service;

import com.prajwal.moneymatters.dto.CreateExpenseRequest;
import com.prajwal.moneymatters.dto.ExpenseResponse;
import com.prajwal.moneymatters.dto.UpdateExpenseRequest;

import java.util.List;

public interface ExpenseService {

    ExpenseResponse create(CreateExpenseRequest request);

    List<ExpenseResponse> getMyExpenses();


    ExpenseResponse update(Long expenseId, UpdateExpenseRequest request);

    void delete(Long expenseId);
}
