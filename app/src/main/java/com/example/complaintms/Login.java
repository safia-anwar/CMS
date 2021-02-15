package com.example.complaintms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText EmailAddress , TextPassword ;
    Button createAccount , Loginbutton;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createAccount = findViewById(R.id.createAccount);
        Loginbutton = findViewById(R.id.Loginbutton);
        EmailAddress = findViewById(R.id.EmailAddress);
        TextPassword = findViewById(R.id.TextPassword);
        DB = new DBHelper(this);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Signup = new Intent(Login.this , Signup.class);
                startActivity(Signup);
            }
        });

        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EmailAddress.getText().toString();
                String password = TextPassword.getText().toString();

                if(email.equals( "") || password.equals("")){
                    Toast.makeText(Login.this , "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkemailpass = DB.checkEmailAddresspassword(email,password);
                    if(checkemailpass == true ){
                        Toast.makeText(Login.this , "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent dashboard = new Intent(Login.this,Dashboard.class);
                        startActivity(dashboard);
                    }
                    else {
                        Toast.makeText(Login.this , "Invalid Credential",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}