package ru.mirea.stulovad.lesson4.serviceap;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class PlayerService extends Service {
    private MediaPlayer mediaPlayer;
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    private static final String ATTRIBUTION_TAG = "player_service_attribution";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(mp -> stopForeground(true));
        }
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // 1. Create Notification Channel
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Music Player Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("MIREA Channel");
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        // 2. Build Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentText("Playing....")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("kakaya_to_music"))
                .setContentTitle("Music Player")
                .setOngoing(true);

        // 3. Start Foreground with Type (Required for Android 14+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(1, builder.build(), ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK);
        } else {
            startForeground(1, builder.build());
        }

        // 4. Create MediaPlayer with Attribution Context (to fix attributionTag error)
        Context context = this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            context = createAttributionContext(ATTRIBUTION_TAG);
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.kakaya_to_music);
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
