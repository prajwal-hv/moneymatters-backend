package com.prajwal.moneymatters.repository;

import com.prajwal.moneymatters.Model.Expense;
import com.prajwal.moneymatters.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser(User user);
}
