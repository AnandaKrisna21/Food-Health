package com.example.foodhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    EditText edt_user, edt_date, edt_email,edt_pass;
    RadioGroup radio_gender;
    RadioButton radiobuttonselectgender;
    Button btn_sign;
    TextView txt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edt_user = findViewById(R.id.et_user);
        edt_date = findViewById(R.id.et_date);
        edt_email = findViewById(R.id.et_mail);
        edt_pass = findViewById(R.id.et_pas);
        btn_sign = findViewById(R.id.bt_sign);
        txt_login = findViewById(R.id.txt_lgn);

        //Radio Button for Gender
        radio_gender = findViewById(R.id.rd_gender);
        radio_gender.clearCheck();


        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectgender = radio_gender.getCheckedRadioButtonId();
                radiobuttonselectgender = findViewById(selectgender);

                //data
                String fullname, date, email, password;
                String textgender;
                fullname = String.valueOf(edt_user.getText());
                date = String.valueOf(edt_date.getText());
                email = String.valueOf(edt_email.getText());
                password = String.valueOf(edt_pass.getText());

                if (TextUtils.isEmpty(fullname)){
                    Toast.makeText(SignupActivity.this, "Enter Fullname", Toast.LENGTH_SHORT).show();
                    edt_user.setError("Fullname is Required");
                    edt_user.requestFocus();
                } else if (TextUtils.isEmpty(date)){
                    Toast.makeText(SignupActivity.this, "Enter Date of Birth", Toast.LENGTH_SHORT).show();
                    edt_date.setError("Date of Birth is Required");
                    edt_date.requestFocus();
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignupActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    edt_email.setError("Email is Required");
                    edt_email.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(SignupActivity.this, "Please Re-enter Email", Toast.LENGTH_SHORT).show();
                    edt_email.setError("Valid Email is Required");
                    edt_email.requestFocus();
                } else if (TextUtils.isEmpty(password)){
                    Toast.makeText(SignupActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    edt_pass.setError("Password is Required");
                    edt_pass.requestFocus();
                } else if (radio_gender.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(SignupActivity.this, "Please Select Your Gender", Toast.LENGTH_SHORT).show();
                    radiobuttonselectgender.setError("Gender is Required");
                    radiobuttonselectgender.requestFocus();
                } else {
                    textgender = radiobuttonselectgender.getText().toString();
                    registerUser(fullname,date,textgender,email,password);
                }
            }
        });
    }

    private void registerUser(String fullname, String date, String textgender, String email, String password) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "Sign Up Succesfull", Toast.LENGTH_SHORT).show();
                            Intent a = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(a);
                            finish();
                        }
                        else {
                            Toast.makeText(SignupActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}