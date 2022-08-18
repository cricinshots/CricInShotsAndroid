package com.indevinfinity.cricinshots;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Read this:
 * DO initialization once:
 * Declare:
 * NotificationProvider np = null;
*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
*       np = new NotificationProvider(getApplicationContext());
*  }
 *  To update notif, do this everytime:
 *  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
 * *       np.build2("Ind vs Aus", "Ind: 250/7")
 * *  }
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class NotificationProvider {
    private Context context;
    public NotificationProvider(Context con){
        context=con;
        setChannel();
    }


    private static final String NOTIFICATION_CHANNEL_ID = "cricin_notification_channel";
    private static final String CHANNEL_NAME = "cricinshots";
    private int importance = NotificationManager.IMPORTANCE_DEFAULT;
    private NotificationChannel notificationChannel;

    private void setChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, CHANNEL_NAME, importance);
            //Boolean value to set if lights are enabled for Notifications from this Channel
            notificationChannel.enableLights(true);
            //Boolean value to set if vibration are enabled for Notifications from this Channel
            notificationChannel.enableVibration(true);
            //Sets the color of Notification Light
            notificationChannel.setLightColor(Color.BLUE);
         /*   //Set the vibration pattern for notifications. Pattern is in milliseconds with the format {delay,play,sleep,play,sleep...}
            notificationChannel.setVibrationPattern(new long[] {
                    500,
                    500,
                    500,
                    500,
                    500
            });*/
            //Sets whether notifications from these Channel should be visible on Lockscreen or not
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }

    }

    private Notification notification;
    public void build2(String teams, String score, int notificationType) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        builder.setContentTitle(teams);
        builder.setContentText(score);
        builder.setSmallIcon(R.drawable.ic_notification_ball);
        if(notificationType==NOTIFICATION_TYPE_PERSISTENT){
            builder.setOngoing(true);
        }
        notification = builder.build();
        show(notificationType);
    }
    //am i acutally supposed to set a random number?
    public static final int NOTIFICATION_TYPE_PERSISTENT = 3536, NOTIFICATION_TYPE_CLEARABLE = 3537;
    private NotificationManagerCompat notificationManagerCompat;
    private void show(int notificationType) {
        notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(notificationType, notification);
    }

    /**
     * clears only persistent notifications
     */
    public void clear() {
        notificationManagerCompat.cancel(NOTIFICATION_TYPE_PERSISTENT);
    }
}
