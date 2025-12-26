package com.prajwal.moneymatters.service.impl;

import com.prajwal.moneymatters.Model.Expense;
import com.prajwal.moneymatters.Model.User;
import com.prajwal.moneymatters.dto.CreateExpenseRequest;
import com.prajwal.moneymatters.dto.ExpenseResponse;
import com.prajwal.moneymatters.dto.UpdateExpenseRequest;
import com.prajwal.moneymatters.exception.BadRequestException;
import com.prajwal.moneymatters.exception.ResourceNotFoundException;
import com.prajwal.moneymatters.repository.ExpenseRepository;
import com.prajwal.moneymatters.service.ExpenseService;
import com.prajwal.moneymatters.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }

    @Override
    public ExpenseResponse create(CreateExpenseRequest request) {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userService.findByUsername(username);

        Expense expense = new Expense();
        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setExpenseDate(request.getExpenseDate());
        expense.setUser(user);

        Expense saved = expenseRepository.save(expense);
        return new ExpenseResponse(saved.getId(),
                saved.getTitle(),
                saved.getCategory(),
                saved.getAmount(),

                saved.getExpenseDate());

    }

    @Override
    public List<ExpenseResponse> getMyExpenses() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findByUsername(username);

        return expenseRepository.findByUser(user)

                .stream()
                .map(e -> new ExpenseResponse(
                        e.getId(),
                        e.getTitle(),
                        e.getCategory(),
                        e.getAmount(),
                        e.getExpenseDate()
                ))
                .toList();
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
}
