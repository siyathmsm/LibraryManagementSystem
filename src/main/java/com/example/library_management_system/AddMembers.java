package com.example.library_management_system;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddMembers extends AppCompatActivity {
    private EditText cardNoEdt, memberNameEdt, addrEdt, tpNoEdt;
    private DBHandler_AddMember dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use setContentView to set the layout for this activity
        setContentView(R.layout.add_members);

        // initializing all our variables.
        cardNoEdt = findViewById(R.id.text_input_1);
        memberNameEdt = findViewById(R.id.text_input_2);
        addrEdt = findViewById(R.id.text_input_3);
        tpNoEdt = findViewById(R.id.text_input_4);
        Button addButton = findViewById(R.id.add_button);

        dbHandler = new DBHandler_AddMember(AddMembers.this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardNo = cardNoEdt.getText().toString();
                String memberName = memberNameEdt.getText().toString();
                String address = addrEdt.getText().toString();
                String contactNo = tpNoEdt.getText().toString();

                if (cardNo.isEmpty() && memberName.isEmpty() && address.isEmpty() && contactNo.isEmpty()) {
                    Toast.makeText(AddMembers.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewMember(cardNo, memberName, address, contactNo);

                Toast.makeText(AddMembers.this, "Member has been added.", Toast.LENGTH_SHORT).show();
                cardNoEdt.setText("");
                memberNameEdt.setText("");
                addrEdt.setText("");
                tpNoEdt.setText("");
            }
        });
    }
}
