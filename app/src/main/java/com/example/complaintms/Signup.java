package com.example.complaintms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class Signup extends AppCompatActivity {


    Button LoginButton , Signupbtn ;
    EditText firstName , Lastname , cellno , EmailAddress,TextPassword,Confirmpassword;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        LoginButton = (Button) findViewById(R.id.Loginbutton);
        Signupbtn = (Button) findViewById(R.id.Signupbtn);
        firstName = (EditText) findViewById(R.id.firstName);
        Lastname = (EditText) findViewById(R.id.Lastname);
        cellno = (EditText) findViewById(R.id.cellno);
        EmailAddress = (EditText) findViewById(R.id.EmailAddress);
        TextPassword = (EditText) findViewById(R.id.TextPassword);
        Confirmpassword = (EditText) findViewById(R.id.Confirmpassword);
        DB = new DBHelper(this);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(Signup.this , Login.class);
                startActivity(Login);
            }
        });

        Signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fn =  firstName.getText().toString();
                String ln = Lastname.getText().toString();
                String cn = cellno.getText().toString();
                String email = EmailAddress.getText().toString();
                String password = TextPassword.getText().toString();
                String repass = Confirmpassword.getText().toString();

                if(fn.equals("") || ln.equals("") || cn.equals("") || email.equals("") || password.equals("")||repass.equals("")){
                    Toast.makeText(Signup.this , "Enter all the fields" , Toast.LENGTH_SHORT).show();
                }
                else {
                    if(password.equals(repass)){
                        Boolean checkEmailAddress = DB.checkEmailAddress(email);
                        if(checkEmailAddress == false){
                            Boolean insert = DB.insertData(fn,ln,cn,email,password);
                            if(insert==true){
                                Toast.makeText(Signup.this , "Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent dashboard = new Intent(Signup.this , Dashboard.class);
                                startActivity(dashboard);
                            }
                            else {
                                Toast.makeText(Signup.this , "Registration Failed",Toast.LENGTH_SHORT).show();

                            }
                        }
                        else {
                            Toast.makeText(Signup.this , "User already exist! Login Your Account",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Signup.this , "Password is incorrect",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


    }
}