package com.example.sayfr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sayfr.entity.PaUserDB;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register_activity extends AppCompatActivity {


    EditText edUsername, edPassword, edBlutooth;

    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        edBlutooth = findViewById(R.id.bltEditRegBlutooth);
        edUsername = findViewById(R.id.EditRegUsername);
        edPassword = findViewById(R.id.passwordEditText);


        registerButton = findViewById(R.id.registerButton);

        /*registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register_activity.this, login_activity.class));
            }
        });*/

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String blutooth = edBlutooth.getText().toString();

                PaUserDB db = new PaUserDB(getApplicationContext(), "SAYFR", null, 1);

                if (username.length() == 0 || password.length() == 0 || blutooth.length() == 0 ) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();




                } else if (isValid(password) == false) {
                    Toast.makeText(getApplicationContext(), "password should be more than 8 characters and contains digits charaster and special char ", Toast.LENGTH_SHORT).show();

                } else {
                    db.register(blutooth, username, password);
                    Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register_activity.this, login_activity.class));


                }
                /*else if (password.compareTo(confirm) == 0) {
                    //if(isValid(password)){
                    db.register(username, email, password);
                    Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Registration.this, MainActivity.class));

                    //}else {

                    //Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, having letters, digits and special symbol", Toast.LENGTH_SHORT).show();}


            }*/
            }


            public boolean isValid(String passwordhere) {
                int f1 = 0, f2 = 0;
                if (passwordhere.length() < 8) {
                    return false;
                } else {
                    for (int p = 0; p < passwordhere.length(); p++) {
                        if (Character.isLetter(passwordhere.charAt(p))) {
                            f1 = 1;
                        }
                    }

                    for (int r = 0; r < passwordhere.length(); r++) {
                        if (Character.isDigit(passwordhere.charAt(r))) {
                            f2 = 1;
                        }
                    }
            /*for (int s= 0 ; s<passwordhere.length(); s++){
                char c = passwordhere.charAt(s);
                if(c>=33&&c<=46 || c==64){
                    f3=1;
                }
            }*/
                    if (f1 == 1 && f2 == 1)
                        return true;
                    return false;
                }
            }



        });
    }

}