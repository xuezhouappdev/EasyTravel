package xuezhou_cs680.easytravel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by wangting on 4/30/16.
 */
public class MyAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"check tomorrow's reminder!",Toast.LENGTH_LONG).show();
        Intent i = new Intent(context, NotificationService.class);
        context.startService(i);


    }
}