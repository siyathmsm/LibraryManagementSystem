package com.example.library_management_system;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookCopy extends AppCompatActivity {
    private EditText bookIdEdt, branchIdEdt, acNoEdt;
    private DBHandler_BookCopy dbHandler;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use setContentView to set the layout for this activity
        setContentView(R.layout.book_copy);

        // initializing all our variables.
        bookIdEdt = findViewById(R.id.text_input_1);
        branchIdEdt = findViewById(R.id.text_input_2);
        acNoEdt = findViewById(R.id.text_input_3);
        addButton = findViewById(R.id.add_button);

        dbHandler = new DBHandler_BookCopy(BookCopy.this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookId = bookIdEdt.getText().toString();
                String branchId = branchIdEdt.getText().toString();
                String acNumber = acNoEdt.getText().toString();

                if (bookId.isEmpty() && branchId.isEmpty() && acNumber.isEmpty()) {
                    Toast.makeText(BookCopy.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.updateBookCopy(bookId, branchId, acNumber);

                Toast.makeText(BookCopy.this, "Book copy has been updated.", Toast.LENGTH_SHORT).show();
                bookIdEdt.setText("");
                branchIdEdt.setText("");
                acNoEdt.setText("");
            }
        });
    }
}
