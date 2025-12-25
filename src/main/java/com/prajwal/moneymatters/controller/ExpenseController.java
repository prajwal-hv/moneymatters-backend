package com.prajwal.moneymatters.controller;


import com.prajwal.moneymatters.dto.CreateExpenseRequest;
import com.prajwal.moneymatters.dto.ExpenseResponse;
import com.prajwal.moneymatters.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ExpenseResponse create(@Valid @RequestBody CreateExpenseRequest request){
        return expenseService.create(request);
    }

    @GetMapping
    public List<ExpenseResponse> getMyExpenses(){
        return expenseService.getMyExpenses();
    }
}
