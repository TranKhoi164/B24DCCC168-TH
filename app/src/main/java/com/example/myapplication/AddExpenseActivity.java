package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editAmount;
    private Spinner spinnerCategory;
    private DatePicker datePicker;
    private EditText editNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        editName = findViewById(R.id.edit_expense_name);
        editAmount = findViewById(R.id.edit_amount);
        spinnerCategory = findViewById(R.id.spinner_category);
        datePicker = findViewById(R.id.date_picker);
        editNote = findViewById(R.id.edit_note);
        Button btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(v -> saveExpense());
    }

    private void saveExpense() {
        String name = editName.getText().toString().trim();
        String amountText = editAmount.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            editName.setError(getString(R.string.error_empty_name));
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            amount = -1;
        }

        if (amount <= 0) {
            editAmount.setError(getString(R.string.error_invalid_amount));
            return;
        }

        String category = (String) spinnerCategory.getSelectedItem();
        String note = editNote.getText().toString().trim();

        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), 0, 0, 0);
        long dateMillis = calendar.getTimeInMillis();

        ExpenseStore.getInstance().add(name, amount, category, dateMillis, note);

        Toast.makeText(this, R.string.btn_save, Toast.LENGTH_SHORT).show();
        finish();
    }
}
