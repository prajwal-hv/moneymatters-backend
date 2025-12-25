package com.prajwal.moneymatters.service;

import com.prajwal.moneymatters.dto.CreateExpenseRequest;
import com.prajwal.moneymatters.dto.ExpenseResponse;

import java.util.List;

public interface ExpenseService {

    ExpenseResponse create(CreateExpenseRequest request);

    List<ExpenseResponse> getMyExpenses();

}
