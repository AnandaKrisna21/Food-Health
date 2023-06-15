package com.example.foodhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    TextView user, date, email, password, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user  = findViewById(R.id.view_name);
        date = findViewById(R.id.view_dob);
        phone = findViewById(R.id.view_phone);
        email = findViewById(R.id.view_email);
        password = findViewById(R.id.view_pass);

        showAllData();
    }

    private void showAllData(){
        Intent intent = getIntent();
        String username = intent.getStringExtra("fullname");
        String userdate = intent.getStringExtra("date");
        String userphone = intent.getStringExtra("phone");
        String useremail = intent.getStringExtra("email");
        String userpass = intent.getStringExtra("password");

        user.setText(username);
        date.setText(userdate);
        phone.setText(userphone);
        email.setText(useremail);
        password.setText(userpass);
    }
}
