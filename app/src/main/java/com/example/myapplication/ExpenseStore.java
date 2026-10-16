package com.example.myapplication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * In-memory data holder shared between activities for this assignment
 * (no database/account system is required by the spec).
 */
public class ExpenseStore {

    private static final ExpenseStore INSTANCE = new ExpenseStore();

    private final List<Expense> expenses = new ArrayList<>();
    private long nextId = 1;

    private ExpenseStore() {
        long today = Calendar.getInstance().getTimeInMillis();
        add("Ăn sáng", 30000, "Ăn uống", today, "");
        add("Đổ xăng", 50000, "Đi lại", today, "");
        add("Mua sách", 120000, "Học tập", today, "");
    }

    public static ExpenseStore getInstance() {
        return INSTANCE;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public Expense add(String name, double amount, String category, long dateMillis, String note) {
        Expense expense = new Expense(nextId++, name, amount, category, dateMillis, note);
        expenses.add(expense);
        return expense;
    }

    public Expense findById(long id) {
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                return expense;
            }
        }
        return null;
    }

    public void remove(long id) {
        Expense expense = findById(id);
        if (expense != null) {
            expenses.remove(expense);
        }
    }

    public double getTotal() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }
}
