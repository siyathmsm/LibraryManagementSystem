package com.example.library_management_system;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateBook extends AppCompatActivity {
    private EditText bookIdInput;
    private EditText titleInput;
    private EditText authorNameInput;
    private EditText publisherNameInput;
    private EditText branchIdInput;
    private Button searchButton;
    private Button updateButton;
    private Button deleteButton;
    private DBHandler_AddBook dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use setContentView to set the layout for this activity
        setContentView(R.layout.update_book);

        // Initialize views
        bookIdInput = findViewById(R.id.book_id_input);
        titleInput = findViewById(R.id.text_input_2);
        authorNameInput = findViewById(R.id.text_input_3);
        publisherNameInput = findViewById(R.id.text_input_4);
        branchIdInput = findViewById(R.id.text_input_5);
        searchButton = findViewById(R.id.search_button);
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.back_button);
        dbHandler = new DBHandler_AddBook(this);

        // Set up search button click listener
        searchButton.setOnClickListener(v -> {
            String bookId = bookIdInput.getText().toString().trim();

            if (bookId.isEmpty()) {
                Toast.makeText(UpdateBook.this, "Please enter a book ID.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Retrieve the book from the database
            Book book = dbHandler.getBookById(bookId);

            if (book != null) {
                // Fill the input fields with the book details
                titleInput.setText(book.getBookTitle());
                authorNameInput.setText(book.getAuthorName());
                publisherNameInput.setText(book.getPublisherName());
                branchIdInput.setText(book.getBranchId());
            } else {
                Toast.makeText(UpdateBook.this, "Book not found.", Toast.LENGTH_SHORT).show();
            }
        });

        // Set up update button click listener
        updateButton.setOnClickListener(v -> {
            String bookId = bookIdInput.getText().toString().trim();
            String title = titleInput.getText().toString().trim();
            String authorName = authorNameInput.getText().toString().trim();
            String publisherName = publisherNameInput.getText().toString().trim();
            String branchId = branchIdInput.getText().toString().trim();

            if (bookId.isEmpty()) {
                Toast.makeText(UpdateBook.this, "Please enter a book ID.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a book object with the updated details
            Book book = new Book();
            book.setBookId(bookId);
            book.setBookTitle(title);
            book.setAuthorName(authorName);
            book.setPublisherName(publisherName);
            book.setBranchId(branchId);

            // Update the book in the database
            dbHandler.updateBook(book);

            Toast.makeText(UpdateBook.this, "Book details updated.", Toast.LENGTH_SHORT).show();
            bookIdInput.setText("");
            titleInput.setText("");
            authorNameInput.setText("");
            publisherNameInput.setText("");
            branchIdInput.setText("");
        });

        // Set up delete button click listener
        deleteButton.setOnClickListener(v -> {
            String bookId = bookIdInput.getText().toString().trim();

            if (bookId.isEmpty()) {
                Toast.makeText(UpdateBook.this, "Please enter a book ID.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Delete the book from the database
            dbHandler.deleteBook(bookId);

            Toast.makeText(UpdateBook.this, "Book deleted.", Toast.LENGTH_SHORT).show();
            bookIdInput.setText("");
            titleInput.setText("");
            authorNameInput.setText("");
            publisherNameInput.setText("");
            branchIdInput.setText("");
        });
    }
}

