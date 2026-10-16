package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_EXPENSE_ID = "extra_expense_id";

    private TextView textTotal;
    private TextView textEmpty;
    private RecyclerView recyclerExpenses;
    private ExpenseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTotal = findViewById(R.id.text_total);
        textEmpty = findViewById(R.id.text_empty);
        recyclerExpenses = findViewById(R.id.recycler_expenses);
        Button btnAddExpense = findViewById(R.id.btn_add_expense);

        recyclerExpenses.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExpenseAdapter(ExpenseStore.getInstance().getExpenses(), this::openDetail);
        recyclerExpenses.setAdapter(adapter);

        btnAddExpense.setOnClickListener(v ->
                startActivity(new Intent(this, AddExpenseActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void openDetail(Expense expense) {
        Intent intent = new Intent(this, ExpenseDetailActivity.class);
        intent.putExtra(EXTRA_EXPENSE_ID, expense.getId());
        startActivity(intent);
    }

    private void refresh() {
        adapter.notifyDataSetChanged();
        textTotal.setText(FormatUtils.formatAmount(ExpenseStore.getInstance().getTotal()));

        boolean empty = ExpenseStore.getInstance().getExpenses().isEmpty();
        textEmpty.setVisibility(empty ? View.VISIBLE : View.GONE);
        recyclerExpenses.setVisibility(empty ? View.GONE : View.VISIBLE);
    }
}
