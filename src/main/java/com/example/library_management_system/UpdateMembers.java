package com.example.library_management_system;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateMembers extends AppCompatActivity {
    private EditText cardNoInput;
    private EditText nameInput;
    private EditText addressInput;
    private EditText contactNumberInput;
    private Button searchButton;
    private Button updateButton;
    private Button deleteButton;
    private DBHandler_AddMember dbHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Use setContentView to set the layout for this activity
        setContentView(R.layout.update_member);

        // Initialize views
        cardNoInput = findViewById(R.id.member_id_input);
        nameInput = findViewById(R.id.text_input_2);
        addressInput = findViewById(R.id.text_input_3);
        contactNumberInput = findViewById(R.id.text_input_4);
        searchButton = findViewById(R.id.search_button);
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);
        dbHandler = new DBHandler_AddMember(this);

        // Set up search button click listener
        searchButton.setOnClickListener(v -> {
            String cardNo = cardNoInput.getText().toString().trim();

            if (cardNo.isEmpty()) {
                Toast.makeText(UpdateMembers.this, "Please enter a Card Number.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Retrieve the book from the database
            Member member = dbHandler.getMemberById(cardNo);

            if (member != null) {
                // Fill the input fields with the book details
                nameInput.setText(member.getMemberName());
                addressInput.setText(member.getAddress());
                contactNumberInput.setText(member.getContactNo());
            } else {
                Toast.makeText(UpdateMembers.this, "Member not found.", Toast.LENGTH_SHORT).show();
            }
        });

        // Set up update button click listener
        updateButton.setOnClickListener(v -> {
            String cardNo = cardNoInput.getText().toString().trim();
            String name = nameInput.getText().toString().trim();
            String addr = addressInput.getText().toString().trim();
            String contactNo = contactNumberInput.getText().toString().trim();

            if (cardNo.isEmpty()) {
                Toast.makeText(UpdateMembers.this, "Please enter a Card Number.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a book object with the updated details
            Member member = new Member();
            member.setCardNo(cardNo);
            member.setMemberName(name);
            member.setAddress(addr);
            member.setContactNo(contactNo);

            // Update the book in the database
            dbHandler.updateMember(member);

            Toast.makeText(UpdateMembers.this, "Member details updated.", Toast.LENGTH_SHORT).show();
            cardNoInput.setText("");
            nameInput.setText("");
            addressInput.setText("");
            contactNumberInput.setText("");
        });

        // Set up delete button click listener
        deleteButton.setOnClickListener(v -> {
            String cardNo = cardNoInput.getText().toString().trim();

            if (cardNo.isEmpty()) {
                Toast.makeText(UpdateMembers.this, "Please enter a Card Number.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Delete the book from the database
            dbHandler.deleteMember(cardNo);

            Toast.makeText(UpdateMembers.this, "Member deleted.", Toast.LENGTH_SHORT).show();
            cardNoInput.setText("");
            nameInput.setText("");
            addressInput.setText("");
            contactNumberInput.setText("");
        });
    }
}