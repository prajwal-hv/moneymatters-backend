package com.prajwal.moneymatters.controller;


import com.prajwal.moneymatters.dto.CreateExpenseRequest;
import com.prajwal.moneymatters.dto.ExpenseResponse;
import com.prajwal.moneymatters.dto.MonthlyExpenseSummaryResponse;
import com.prajwal.moneymatters.dto.UpdateExpenseRequest;
import com.prajwal.moneymatters.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    public Page<ExpenseResponse> getMyExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return expenseService.getMyExpenses( PageRequest.of(page, size));
    }

    @PutMapping("/{id}")
    public ExpenseResponse update(@PathVariable Long id, @Valid @RequestBody UpdateExpenseRequest request){
        return expenseService.update(id,request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        expenseService.delete(id);
    }

    @GetMapping("/summary/monthly")
    public MonthlyExpenseSummaryResponse getMonthlySummary(@RequestParam int year, @RequestParam int month){
        return expenseService.getMonthlySummary(year, month);
    }
}
