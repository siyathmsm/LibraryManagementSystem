package com.example.library_management_system;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ViewBook extends AppCompatActivity {
    private EditText bookIdSearchEdt;
    private TextView searchResultText;
    private DBHandler_AddBook dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_book);

        // Initialize UI components
        bookIdSearchEdt = findViewById(R.id.book_id_search_edt);
        Button searchButton = findViewById(R.id.search_button);
        searchResultText = findViewById(R.id.search_result_text);

        // Initialize the database handler
        dbHandler = new DBHandler_AddBook(ViewBook.this);

        // Set up the search button's onClickListener
        searchButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                // Retrieve the inputted book ID
                String bookId = bookIdSearchEdt.getText().toString().trim();

                // Check if the book ID input is empty
                if (bookId.isEmpty()) {
                    Toast.makeText(ViewBook.this, "Please enter a book ID.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Attempt to fetch the book from the database
                try {
                    Book book = dbHandler.getBookById(bookId);

                    // If a book was found, display its details
                    if (book != null) {
                        String bookDetails = "Book ID: " + book.getBookId() + "\n"
                                + "Title: " + book.getBookTitle() + "\n"
                                + "Author: " + book.getAuthorName() + "\n"
                                + "Publisher: " + book.getPublisherName() + "\n"
                                + "Branch ID: " + book.getBranchId();
                        searchResultText.setText(bookDetails);
                    } else {
                        // If no book was found, display a "not found" message
                        searchResultText.setText("Book not found.");
                    }
                } catch (Exception e) {
                    // Handle any exceptions during the database query
                    Toast.makeText(ViewBook.this, "An error occurred while searching for the book.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        Button viewAllBooksButton = findViewById(R.id.view_all_books_button);
        viewAllBooksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetch all books from the database
                List<Book> books = dbHandler.getAllBooks();

                // Check if any books were found
                if (books.isEmpty()) {
                    searchResultText.setText("No books found.");
                    return;
                } else {// Build a string with details of all books
                    StringBuilder booksDetails = new StringBuilder();
                    for (Book book : books) {
                        booksDetails.append("Book ID: ").append(book.getBookId()).append("\n")
                                .append("Title: ").append(book.getBookTitle()).append("\n")
                                .append("Author: ").append(book.getAuthorName()).append("\n")
                                .append("Publisher: ").append(book.getPublisherName()).append("\n")
                                .append("Branch ID: ").append(book.getBranchId()).append("\n\n");
                    }

                    // Display the details of all books
                    searchResultText.setText(booksDetails.toString());
                }

            }
        });

    }
}
