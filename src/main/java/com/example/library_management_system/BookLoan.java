package com.example.library_management_system;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookLoan extends AppCompatActivity {
    private EditText accessNumberInput, branchIdInput, cardNumberInput, outDateInput, dueDateInput, returnedDateInput;
    private DBHandler_BookLoan dbHandler;
    private Button issueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use setContentView to set the layout for this activity
        setContentView(R.layout.book_loan);

        // Initialize UI components
        accessNumberInput = findViewById(R.id.text_input_1);
        branchIdInput = findViewById(R.id.text_input_2);
        cardNumberInput = findViewById(R.id.text_input_3);
        outDateInput = findViewById(R.id.text_input_4);
        dueDateInput = findViewById(R.id.text_input_5);
        returnedDateInput = findViewById(R.id.text_input_6);
        issueButton = findViewById(R.id.add_button);

        // Initialize the database handler
        dbHandler = new DBHandler_BookLoan(BookLoan.this);

        // Set up the "Issue" button's onClickListener
        issueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the access number input
                String accessNumber = accessNumberInput.getText().toString().trim();

                // Check the access number
                if (accessNumber.equals("AV01")) {
                    // Access number is valid

                    // Gather the data from the input fields
                    String branchId = branchIdInput.getText().toString().trim();
                    String cardNumber = cardNumberInput.getText().toString().trim();
                    String outDate = outDateInput.getText().toString().trim();
                    String dueDate = dueDateInput.getText().toString().trim();
                    String returnedDate = returnedDateInput.getText().toString().trim();

                    // Create a BookLoan object (or other appropriate object) to store the data
                    //BookLoanData bookLoan = new BookLoanData(accessNumber, branchId, cardNumber, outDate, dueDate, returnedDate);

                    // Save the data to the database using the dbHandler
                    boolean isSuccess = dbHandler.updateBookLoan(accessNumber, branchId, cardNumber, outDate, dueDate, returnedDate);

                    if (isSuccess) {
                        // Display a success message
                        Toast.makeText(BookLoan.this, "Book issued successfully!", Toast.LENGTH_SHORT).show();

                        // Clear the input fields
                        clearInputFields();
                    } else {
                        // Display an error message
                        Toast.makeText(BookLoan.this, "Failed to issue the book. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Access number is not valid
                    // Display an error message
                    Toast.makeText(BookLoan.this, "The book is not available.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Helper method to clear input fields
    private void clearInputFields() {
        accessNumberInput.setText("");
        branchIdInput.setText("");
        cardNumberInput.setText("");
        outDateInput.setText("");
        dueDateInput.setText("");
        returnedDateInput.setText("");
    }
}
