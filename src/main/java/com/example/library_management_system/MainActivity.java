package com.example.library_management_system;

import static com.example.library_management_system.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.admin_dashboard);

        // Find the toolbar from the layout
        Toolbar toolbar = findViewById(R.id.toolbar);

        // Set the toolbar as the app bar for the activity
        setSupportActionBar(toolbar);

        // Set the title for the toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Dashboard");
        }

        //Option 1 : Add Book
        ImageView option1 = findViewById(id.image1_1);
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBooks.class);
                startActivity(intent);
            }
        });

        //Option 2 : Update Book
        ImageView option2 = findViewById(id.image1_2);
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateBook.class);
                startActivity(intent);
            }
        });

        //Option 3 : View Book
        ImageView option3 = findViewById(id.image1_3);
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewBook.class);
                startActivity(intent);
            }
        });

        //Option 4 : Add Members
        ImageView option4 = findViewById(id.image2_1);
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMembers.class);
                startActivity(intent);
            }
        });

        //Option 5 : Update Member
        ImageView option5 = findViewById(id.image2_2);
        option5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateMembers.class);
                startActivity(intent);
            }
        });

        //Option 6 : View Members
        ImageView option6 = findViewById(id.image2_3);
        option6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewMembers.class);
                startActivity(intent);
            }
        });

        //Option 7 : Book Loan
        ImageView option7 = findViewById(id.image3_1);
        option7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookLoan.class);
                startActivity(intent);
            }
        });

        //Option 8 : Return Book
        ImageView option8 = findViewById(id.image3_2);
        option8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReturnBook.class);
                startActivity(intent);
            }
        });

        //Option 9 : Book Copy
        ImageView option9 = findViewById(id.image3_3);
        option9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookCopy.class);
                startActivity(intent);
            }
        });

        //Option 10 : Publisher
        ImageView option10 = findViewById(id.image4_1);
        option10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Publisher.class);
                startActivity(intent);
            }
        });

        //Option 11 : Book Author
        ImageView option11 = findViewById(id.image4_2);
        option11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookAuthor.class);
                startActivity(intent);
            }
        });

        //Option 12 : Branch
        ImageView option12 = findViewById(id.image4_3);
        option12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Branch.class);
                startActivity(intent);
            }
        });

    }
}