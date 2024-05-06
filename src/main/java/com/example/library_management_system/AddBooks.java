package com.example.library_management_system;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddBooks extends AppCompatActivity {
    private EditText bookIdEdt, bookTitleEdt, authorNameEdt, pubNameEdt, branchIdEdt;
    private DBHandler_AddBook dbHandler;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use setContentView to set the layout for this activity
        setContentView(R.layout.add_books);

        // initializing all our variables.
        bookIdEdt = findViewById(R.id.text_input_1);
        bookTitleEdt = findViewById(R.id.text_input_2);
        authorNameEdt = findViewById(R.id.text_input_3);
        pubNameEdt = findViewById(R.id.text_input_4);
        branchIdEdt = findViewById(R.id.text_input_5);
        addButton = findViewById(R.id.add_button);

        dbHandler = new DBHandler_AddBook(AddBooks.this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookId = bookIdEdt.getText().toString();
                String bookTitle = bookTitleEdt.getText().toString();
                String authorName = authorNameEdt.getText().toString();
                String publisherName = pubNameEdt.getText().toString();
                String branchId = branchIdEdt.getText().toString();

                if (bookId.isEmpty() && bookTitle.isEmpty() && authorName.isEmpty() && publisherName.isEmpty() && branchId.isEmpty()) {
                    Toast.makeText(AddBooks.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewBook(bookId, bookTitle, authorName, publisherName, branchId);

                Toast.makeText(AddBooks.this, "Book has been added.", Toast.LENGTH_SHORT).show();
                bookIdEdt.setText("");
                bookTitleEdt.setText("");
                authorNameEdt.setText("");
                pubNameEdt.setText("");
                branchIdEdt.setText("");
            }
        });
    }
}
