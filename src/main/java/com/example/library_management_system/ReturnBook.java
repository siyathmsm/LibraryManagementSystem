package com.example.library_management_system;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReturnBook extends AppCompatActivity {
    private EditText branchIdEdt, cardNoEdt, outDateEdt, dueDateEdt, returnDateEdt;
    private DBHandler_ReturnBook dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use setContentView to set the layout for this activity
        setContentView(R.layout.return_book);

        // initializing all our variables.
        branchIdEdt = findViewById(R.id.text_input_2);
        cardNoEdt = findViewById(R.id.text_input_3);
        outDateEdt = findViewById(R.id.text_input_4);
        dueDateEdt = findViewById(R.id.text_input_5);
        returnDateEdt = findViewById(R.id.text_input_6);
        Button returnButton = findViewById(R.id.add_button);

        dbHandler = new DBHandler_ReturnBook(ReturnBook.this);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String branchId = branchIdEdt.getText().toString();
                String cardNo = cardNoEdt.getText().toString();
                String outDate = outDateEdt.getText().toString();
                String dueDate = dueDateEdt.getText().toString();
                String returnDate = returnDateEdt.getText().toString();

                if (branchId.isEmpty() && cardNo.isEmpty() && outDate.isEmpty() && dueDate.isEmpty() && returnDate.isEmpty()) {
                    Toast.makeText(ReturnBook.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.returnBook(branchId, cardNo, outDate, dueDate, returnDate);

                Toast.makeText(ReturnBook.this, "Book has been returned.", Toast.LENGTH_SHORT).show();
                branchIdEdt.setText("");
                cardNoEdt.setText("");
                outDateEdt.setText("");
                dueDateEdt.setText("");
                returnDateEdt.setText("");
            }
        });
    }
}
