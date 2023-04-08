package com.example.ui;

import static com.example.ui.R.id.imageView21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(),"Welcome " + username, Toast.LENGTH_SHORT ).show();



        ImageView exit = findViewById(imageView21);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));

            }
        });

        ImageView pat;
        pat = findViewById(R.id.imageView8);
        pat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(HomeActivity.this, PatientsActivity.class));
            }
        });





    }

    public class MyWearableListenerService extends WearableListenerService {

        @Override
        public void onMessageReceived(MessageEvent messageEvent) {
            if (messageEvent.getPath().equals("vibrate")) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                }
            }
        }
    }
    Button urgent;

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

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        }

        // Set vibration and sound on the notification object
        long[] vibrationPattern = new long[0];
        notification.vibrate = vibrationPattern;
        notification.sound = Settings.System.DEFAULT_NOTIFICATION_URI;

        // Use the notification manager to send the notification to the phone
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(1, notification);
    }


}