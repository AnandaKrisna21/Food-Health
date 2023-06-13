package com.example.foodhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText edt_email,edt_pass;
    Button btn_login;
    TextView txt_sign;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         edt_email = findViewById(R.id.et_email);
         edt_pass = findViewById(R.id.et_pass);
         btn_login = findViewById(R.id.bt_login);
         txt_sign = findViewById(R.id.txt_signup);

         txt_sign.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                 startActivity(i);
                 finish();
             }
         });

         btn_login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String email, password;
                 email = String.valueOf(edt_email.getText());
                 password = String.valueOf(edt_pass.getText());

                 if (TextUtils.isEmpty(email)){
                     Toast.makeText(LoginActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if (TextUtils.isEmpty(password)){
                     Toast.makeText(LoginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                     return;
                 }

                 firebaseAuth.signInWithEmailAndPassword(email,password)
                         .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                             @Override
                             public void onComplete(@NonNull Task<AuthResult> task) {
                                 if (task.isSuccessful()) {
                                     Toast.makeText(LoginActivity.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                                     Intent a = new Intent(LoginActivity.this, HomeActivity.class);
                                     startActivity(a);
                                     finish();
                                 }
                                 else {
                                     Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                 }
                             }
                         });
             }
         });
    }
}