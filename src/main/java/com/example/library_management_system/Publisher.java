package com.example.library_management_system;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Publisher extends AppCompatActivity {
    private EditText nameEdt, addrEdt, tpNoEdt;
    private DBHandler_AddPublisher dbHandler;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use setContentView to set the layout for this activity
        setContentView(R.layout.publisher);

        // initializing all our variables.
        nameEdt = findViewById(R.id.text_input_1);
        addrEdt = findViewById(R.id.text_input_2);
        tpNoEdt = findViewById(R.id.text_input_3);
        addButton = findViewById(R.id.add_button);

        dbHandler = new DBHandler_AddPublisher(Publisher.this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pubName = nameEdt.getText().toString();
                String address = addrEdt.getText().toString();
                String contactNumber = tpNoEdt.getText().toString();

                if (pubName.isEmpty() && address.isEmpty() && contactNumber.isEmpty()) {
                    Toast.makeText(Publisher.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewPublisher(pubName, address, contactNumber);

                Toast.makeText(Publisher.this, "Publisher has been added.", Toast.LENGTH_SHORT).show();
                nameEdt.setText("");
                addrEdt.setText("");
                tpNoEdt.setText("");
            }
        });
    }
}
