package com.prajwal.moneymatters.service.impl;

import com.prajwal.moneymatters.Model.Expense;
import com.prajwal.moneymatters.Model.User;
import com.prajwal.moneymatters.dto.CreateExpenseRequest;
import com.prajwal.moneymatters.dto.ExpenseResponse;
import com.prajwal.moneymatters.dto.MonthlyExpenseSummaryResponse;
import com.prajwal.moneymatters.dto.UpdateExpenseRequest;
import com.prajwal.moneymatters.exception.BadRequestException;
import com.prajwal.moneymatters.exception.ResourceNotFoundException;
import com.prajwal.moneymatters.repository.ExpenseRepository;
import com.prajwal.moneymatters.service.ExpenseService;
import com.prajwal.moneymatters.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private static final Logger log = LoggerFactory.getLogger(ExpenseServiceImpl.class);
    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }

    @Override
    public ExpenseResponse create(CreateExpenseRequest request) {

        log.info("Create Request"+request.toString());

        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userService.findByUsername(username);

        Expense expense = new Expense();
        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setExpenseDate(request.getDate());
        expense.setUser(user);

        Expense saved = expenseRepository.save(expense);
        return new ExpenseResponse(saved.getId(),
                saved.getTitle(),
                saved.getCategory(),
                saved.getAmount(),

                saved.getExpenseDate());

    }

    @Override
    public Page<ExpenseResponse> getMyExpenses(Pageable pageable) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findByUsername(username);

        return expenseRepository.findByUser(user, pageable)
                .map(e -> new ExpenseResponse(
                        e.getId(),
                        e.getTitle(),
                        e.getCategory(),
                        e.getAmount(),
                        e.getExpenseDate()
                ));
    }

    @Override
    public ExpenseResponse update(Long expenseId, UpdateExpenseRequest request){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findByUsername(userName);

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(()->new ResourceNotFoundException("Expense Not Found"));

//        Authorization Checks
        if(!expense.getUser().getId().equals(user.getId())){
            throw new BadRequestException("You are not allowed to update this expense");
        }

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setExpenseDate(request.getExpenseDate());

        Expense updated = expenseRepository.save(expense);

        return new ExpenseResponse(updated);

    }

    @Override
    public void delete(Long expenseId){

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(()-> new ResourceNotFoundException("Expense Not Found"));

//        Authorization checks
        if(!expense.getUser().getId().equals(user.getId())){
            throw new BadRequestException("You are not allowed to delete this expense");
        }

        expenseRepository.delete(expense);

    }

    @Override
    public MonthlyExpenseSummaryResponse getMonthlySummary(int year, int month){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);
        BigDecimal total = expenseRepository.getMonthlyTotal(user, year, month);

        List<Object[]> rows = expenseRepository.getMonthlyTotalByCategory(user, year, month);

        Map<String, BigDecimal> byCategory = new HashMap<>();
        for(Object[] row: rows){
            byCategory.put(
                    (String) row[0],
                    (BigDecimal) row[1]
            );
        }

        return new MonthlyExpenseSummaryResponse(year, month, total, byCategory);
    }


}
