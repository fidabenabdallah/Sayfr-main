package com.example.sayfr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage_activity extends AppCompatActivity {

    private Button urgent;
    private Button sendmessage;
    private Button senddata;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home_page);



        // Initialize the TextView
        textView2 = findViewById(R.id.textView2);



        // Set the username to the TextView


        urgent = findViewById(R.id.urgent);
        sendmessage = findViewById(R.id.sendmessage);
        senddata = findViewById(R.id.senddata);

        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(),"Welcome " + username, Toast.LENGTH_SHORT ).show();

        textView2.setText("Welcome, " + username);

        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomePage_activity.this, sendmsg_activity.class));

            }
        });
    }
}