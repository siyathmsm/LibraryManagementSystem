package com.example.library_management_system;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookAuthor extends AppCompatActivity {
    private EditText bookIdEdt, authorNameEdt;
    private DBHandler_AddAuthor dbHandler;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use setContentView to set the layout for this activity
        setContentView(R.layout.book_author);

        // initializing all our variables.
        bookIdEdt = findViewById(R.id.text_input_1);
        authorNameEdt = findViewById(R.id.text_input_2);
        addButton = findViewById(R.id.add_button);

        dbHandler = new DBHandler_AddAuthor(BookAuthor.this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookId = bookIdEdt.getText().toString();
                String authorName = authorNameEdt.getText().toString();

                if (bookId.isEmpty() && authorName.isEmpty()) {
                    Toast.makeText(BookAuthor.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewAuthor(bookId, authorName);

                Toast.makeText(BookAuthor.this, "Author has been added.", Toast.LENGTH_SHORT).show();
                bookIdEdt.setText("");
                authorNameEdt.setText("");
            }
        });
    }
}
