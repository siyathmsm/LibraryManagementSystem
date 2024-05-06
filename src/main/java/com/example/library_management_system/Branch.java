package com.example.library_management_system;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Branch extends AppCompatActivity {
    private EditText branchIdEdt, nameEdt, addrEdt;
    private DBHandler_AddBranch dbHandler;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use setContentView to set the layout for this activity
        setContentView(R.layout.branch);

        // initializing all our variables.
        branchIdEdt = findViewById(R.id.text_input_1);
        nameEdt = findViewById(R.id.text_input_2);
        addrEdt = findViewById(R.id.text_input_3);
        addButton = findViewById(R.id.add_button);

        dbHandler = new DBHandler_AddBranch(Branch.this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String branchId = branchIdEdt.getText().toString();
                String branchName = nameEdt.getText().toString();
                String address = addrEdt.getText().toString();

                if (branchId.isEmpty() && branchName.isEmpty() && address.isEmpty()) {
                    Toast.makeText(Branch.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewBranch(branchId, branchName, address);

                Toast.makeText(Branch.this, "Branch has been added.", Toast.LENGTH_SHORT).show();
                branchIdEdt.setText("");
                nameEdt.setText("");
                addrEdt.setText("");
            }
        });
    }
}
