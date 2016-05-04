package xuezhou_cs680.easytravel;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.RemoteViews;

/**
 * Created by wangting on 4/30/16.
 */
public class NotificationService extends Service {
    // private NotificationManager nm;
    // private Object getAppicationContext;
    private Notification myNotify;
    private Button cancel;
    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {

        // getting Notification Service
        final NotificationManager mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // what happen when click the notification ---direct to the main page
        Intent intent1 = new Intent(this.getApplicationContext(), ReminderActivity.class);


        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_ONE_SHOT);

        // define notification details
        myNotify = new Notification.Builder(this)
                .setContentTitle("Alert")
                .setContentText("alert alert ")
                .setSmallIcon(R.drawable.icon)
                .setContentIntent(pendingNotificationIntent)
                .addAction(0,"Alert alert alert", pendingNotificationIntent)
                .build();

        // start the notification
        mManager.notify(1, myNotify);

    }
}
