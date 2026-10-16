package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExpenseDetailActivity extends AppCompatActivity {

    private long expenseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

        TextView textName = findViewById(R.id.text_detail_name);
        TextView textAmount = findViewById(R.id.text_detail_amount);
        TextView textCategory = findViewById(R.id.text_detail_category);
        TextView textDate = findViewById(R.id.text_detail_date);
        TextView textNote = findViewById(R.id.text_detail_note);
        Button btnBack = findViewById(R.id.btn_back);
        Button btnDelete = findViewById(R.id.btn_delete);

        expenseId = getIntent().getLongExtra(MainActivity.EXTRA_EXPENSE_ID, -1);
        Expense expense = ExpenseStore.getInstance().findById(expenseId);

        if (expense == null) {
            finish();
            return;
        }

        textName.setText(expense.getName());
        textAmount.setText(FormatUtils.formatAmount(expense.getAmount()));
        textCategory.setText(getString(R.string.label_category) + ": " + expense.getCategory());
        textDate.setText(getString(R.string.label_date) + ": " + FormatUtils.formatDate(expense.getDateMillis()));

        String note = expense.getNote();
        textNote.setText(getString(R.string.label_note) + ": " + (note == null || note.isEmpty() ? "-" : note));

        btnBack.setOnClickListener(v -> finish());
        btnDelete.setOnClickListener(v -> {
            ExpenseStore.getInstance().remove(expenseId);
            finish();
        });
    }
}
