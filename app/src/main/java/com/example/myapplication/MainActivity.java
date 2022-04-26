package com.example.myapplication;

import static com.example.myapplication.MyApplication.CHANNEL_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSendNotification = findViewById(R.id.btn_send_notification);
        btnSendNotification.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sendNotificationMedia();
            }
        });
    }

    private void sendNotificationMedia() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_music);

        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(this, "tag");

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setPriority(Notification.PRIORITY_MAX)
                .setSmallIcon(R.drawable.ic_small_music)
                .setSubText("poochswadge")
                .setContentTitle("Title of song")
                .setContentText("Singer of the song")
                .setLargeIcon(bitmap)
                // Add media control buttons
                .addAction(R.drawable.ic_back, "Previous", null) //0
                .addAction(R.drawable.ic_pause, "Pause", null) //0
                .addAction(R.drawable.ic_next, "Next", null) //0
                // Apply the media style template
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1 /* #1: pause button */)
                        .setMediaSession(mediaSessionCompat.getSessionToken()))
                .build();

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1, notification);
    }
}