package com.prajwal.moneymatters.repository;

import com.prajwal.moneymatters.Model.Expense;
import com.prajwal.moneymatters.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser(User user);

    @Query("""
SELECT COALESCE(SUM(e.amount), 0)
FROM Expense e
WHERE e.user = :user
AND YEAR(e.expenseDate) = :year
AND MONTH(e.expenseDate) = :month

""")
    BigDecimal getMonthlyTotal(User user, int year,  int month);

    @Query("""
SELECT e.category, COALESCE(SUM(e.amount), 0)
FROM Expense e
WHERE e.user = :user
AND YEAR(e.expenseDate) = :year
AND Month(e.expenseDate) = :month
GROUP BY e.category
""")
    List<Object[]> getMonthlyTotalByCategory(User user, int year, int month);
}
