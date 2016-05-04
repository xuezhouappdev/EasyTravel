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
import android.widget.RemoteViews;

/**
 * Created by wangting on 4/30/16.
 */
public class NotificationService extends Service {
    private NotificationManager nm;
    private Object getAppicationContext;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onStart(Intent intent, int startId){
        super.onStart(intent, startId);
        NotificationManager mManager = (NotificationManager) this.getApplicationContext()
                .getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);



        Intent intent1 = new Intent(this.getApplicationContext(), ReminderActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(
                this.getApplicationContext(), 0, intent1,
                PendingIntent.FLAG_ONE_SHOT);



        Notification notification=new Notification();
        notification.icon=R.drawable.icon;
        notification.tickerText="Alert!";
        RemoteViews contentView=new RemoteViews(getPackageName(),R.layout.content_notification);


        contentView.setTextViewText(R.id.text,"Alert");
        notification.contentView=contentView;
        notification.contentIntent= pendingNotificationIntent;
        mManager.notify(1,notification);
    }



    public void onDestroy() {

        String tag = "Done";
        Log.e(tag, "Alert has been done");
        super.onDestroy();
    }
    public class NotificationBuilderCompat extends Notification.Builder {

        public NotificationBuilderCompat(Context context) {
            super(context);
        }

/*    // 20
    public NotificationBuilderCompat addAction (Notification.Action action) {
        return this;
    }*/
        // 16

        public static final int PRIORITY_DEFAULT = 0x00000000;
        public static final int PRIORITY_HIGH = 0x00000001;
        public static final int PRIORITY_LOW = 0xffffffff;
        public static final int PRIORITY_MAX = 0x00000002;
        public static final int PRIORITY_MIN = 0xfffffffe;

        public NotificationBuilderCompat addAction (int icon, CharSequence title, PendingIntent intent) {
            if (Build.VERSION.SDK_INT >= 16) {
                super.addAction(icon, title, intent);
            }
            return this;
        }
        /*    // 20
            public NotificationBuilderCompat addExtras (Bundle extras) {
                return this;
            }
            // 21
            public NotificationBuilderCompat addPerson (String uri) {
                return this;
            }
        */
        // 16
        public Notification build() {
            if (Build.VERSION.SDK_INT >= 16) {
                return super.build();
            } else {
                return super.getNotification();
            }
        }
/*
    // 20
    public NotificationBuilderCompat extend(Notification.Extender extender) {
        return this;
    }
    // 21
    public NotificationBuilderCompat setCategory (String category) {
        return this;
    }
    // 21
    public NotificationBuilderCompat setColor (int argb) {
        return this;
    }*/

        // 19
        public NotificationBuilderCompat setExtras (Bundle extras) {
            if (Build.VERSION.SDK_INT >= 19) {
                super.setExtras(extras);
            }
            return this;
        }
        /*    // 20
            public NotificationBuilderCompat setGroup(String groupKey) {
                return this;
            }
            // 20
            public NotificationBuilderCompat setGroupSummary (boolean isGroupSummary) {
                return this;
            }
            // 20
            public NotificationBuilderCompat setLocalOnly (boolean localOnly) {
                return this;
            }*/
        // 16
        public NotificationBuilderCompat setPriority (int pri) {
            if (Build.VERSION.SDK_INT >= 16) {
                super.setPriority(pri);
            }
            return this;
        }
        /*    // 21
            public NotificationBuilderCompat setPublicVersion (Notification n) {
                 return this;
             }*/
        // 17
        public NotificationBuilderCompat setShowWhen (boolean show) {
            if (Build.VERSION.SDK_INT >= 17) {
                super.setShowWhen(show);
            }
            return this;
        }
        /*    // 20
            public NotificationBuilderCompat setSortKey (String sortKey) {
                return this;
            }
            // 21
            public NotificationBuilderCompat setSound (Uri sound, AudioAttributes audioAttributes) {
                return this;
            }*/
        // 16
        public NotificationBuilderCompat setStyle (Notification.Style style) {
            if (Build.VERSION.SDK_INT >= 16) {
                super.setStyle(style);
            }
            return this;
        }
        // 16
        public NotificationBuilderCompat setSubText (CharSequence text) {
            if (Build.VERSION.SDK_INT >= 16) {
                super.setSubText(text);
            }
            return this;
        }
        // 16
        public NotificationBuilderCompat setUsesChronometer (boolean b) {
            if (Build.VERSION.SDK_INT >= 16) {
                super.setUsesChronometer(b);
            }
            return this;
        }
/*    // 21
    public NotificationBuilderCompat setVisibility (int visibility) {
        return this;
    }*/
    }
}
