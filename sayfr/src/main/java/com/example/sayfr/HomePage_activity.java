package com.example.sayfr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Wearable;


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

    public void urgent(View view) {
        // Create a notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("Urgent Notification")
                .setContentText("This is an urgent notification from your wearable device.")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        // Create a wearable extender and add additional information
        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender()
                .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
                .setContentIcon(R.drawable.ic_launcher_background)
                .setHintHideIcon(true);

        // Add the wearable extender to the notification builder
        builder.extend(wearableExtender);

        // Build the notification
        Notification notification = builder.build();

        // Set vibration and sound on the notification object
        long[] vibrationPattern = new long[0];
        notification.vibrate = vibrationPattern;
        notification.sound = Settings.System.DEFAULT_NOTIFICATION_URI;

        // Use the notification manager to send the notification to the phone
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(1, notification);

        // Send a message to the phone to vibrate
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .build();
        googleApiClient.connect();
        Wearable.MessageApi.sendMessage(googleApiClient, "", "/vibrate", null);
        googleApiClient.disconnect();


    }
}
    /*public void urgent(View view){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("title page 1");
        builder.setContentText("this is urgent");
        builder.setSmallIcon(R.drawable.ic_launcher_background);

        Notification notification = builder.build();

        NotificationManagerCompat managerCompt = NotificationManagerCompat.from(this);
        managerCompt.notify(1,notification);
    }*/


