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

public class ViewMembers extends AppCompatActivity {
    private EditText cardNoSearchEdt;
    private TextView searchResultText;
    private DBHandler_AddMember dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_member);

        // Initialize UI components
        cardNoSearchEdt = findViewById(R.id.member_id_search_edt);
        Button searchButton = findViewById(R.id.search_button);
        searchResultText = findViewById(R.id.search_result_text);

        // Initialize the database handler
        dbHandler = new DBHandler_AddMember(ViewMembers.this);

        // Set up the search button's onClickListener
        searchButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                // Retrieve the inputted book ID
                String cardNo = cardNoSearchEdt.getText().toString().trim();

                // Check if the book ID input is empty
                if (cardNo.isEmpty()) {
                    Toast.makeText(ViewMembers.this, "Please enter a book ID.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Attempt to fetch the book from the database
                try {
                    Member member = dbHandler.getMemberById(cardNo);

                    // If a book was found, display its details
                    if (member != null) {
                        String memberDetails = "Card No: " + member.getCardNo() + "\n"
                                + "Name: " + member.getMemberName() + "\n"
                                + "Address: " + member.getAddress() + "\n"
                                + "Contact No: " + member.getContactNo();
                        searchResultText.setText(memberDetails);
                    } else {
                        // If no book was found, display a "not found" message
                        searchResultText.setText("Member not found.");
                    }
                } catch (Exception e) {
                    // Handle any exceptions during the database query
                    Toast.makeText(ViewMembers.this, "An error occurred while searching for the book.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        Button viewAllMembersButton = findViewById(R.id.view_all_members_button);
        viewAllMembersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetch all books from the database
                List<Member> members = dbHandler.getAllMembers();

                // Check if any books were found
                if (members.isEmpty()) {
                    searchResultText.setText("No members found.");
                    return;
                } else {// Build a string with details of all books
                    StringBuilder membersDetails = new StringBuilder();
                    for (Member member : members) {
                        membersDetails.append("Book ID: ").append(member.getCardNo()).append("\n")
                                .append("Title: ").append(member.getMemberName()).append("\n")
                                .append("Author: ").append(member.getAddress()).append("\n")
                                .append("Publisher: ").append(member.getContactNo()).append("\n\n");
                    }

                    // Display the details of all books
                    searchResultText.setText(membersDetails.toString());
                }

            }
        });

    }
}