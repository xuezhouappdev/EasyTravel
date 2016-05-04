package xuezhou_cs680.easytravel;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by wangting on 4/30/16.
 */
public class ReminderActivity extends Activity implements AdapterView.OnItemClickListener{

    final int PICK1 = Menu.FIRST + 1;
    final int PICK2 = Menu.FIRST + 2;
    final int PICK3 = Menu.FIRST + 3;
    final int PICK4 = Menu.FIRST + 4;
    final int PICK5 = Menu.FIRST + 5;

    private EditText enterdata;
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<String> number = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private String items = "";
    private ListView listview;

    private final String file = "Reminderlist.txt";
    private static final String tag = "tags";
    private OutputStreamWriter out;

    //private NotificationManager mNotificationManager;
    // private AlarmManager am;
    //private Notification notifyDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_main);
        enterdata = (EditText) findViewById(R.id.enterdata);
        listview = (ListView)findViewById(R.id.list);
        listview.setOnItemClickListener(this);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, number);
        listview.setAdapter(adapter);

        try {
            String path = getFilesDir() + "/Reminderlist.txt";
            File list = new File(path);

            if (list.exists()) {

                //open stream for reading from file
                InputStream in = openFileInput(file);
                InputStreamReader isr = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(isr);
                String str = null;

                while ((str = reader.readLine()) != null) {
                    data.add(str);
                }
                for (int i = 0; i < data.size(); i++) {
                    number.add((i + 1) + ". " + data.get(i));
                }
                reader.close();
            }

        } catch (IOException e) {
        }



        handleNotification();


// define a button and the method to close all notification
        Button cancel = (Button)findViewById(R.id.btn_clear);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final NotificationManager mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mManager.cancelAll();
            }
        });
    }


    private void handleNotification() {


        Intent alarmIntent = new Intent(this,MyAlarm.class);
        //create pendingIntent that will start a broadcast
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //*** set the date and time ***//
        Calendar c = Calendar.getInstance();
        //c.set(year,month,day,hour,minute);
        c.set(Calendar.HOUR, 02);
        c.set(Calendar.MINUTE,39);
        c.set(Calendar.SECOND, 00);
        c.set(Calendar.AM_PM, Calendar.PM);

        // create an alarm to trigger to alarmReceiver when the data and time is 5 hours before
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        MenuItem item1 = menu.add(0, PICK1, Menu.NONE, "Save List");
        MenuItem item2 = menu.add(0, PICK2, Menu.NONE, "Back To MainMenu");
        MenuItem item3 = menu.add(0, PICK3, Menu.NONE, "Add Entry");
        MenuItem item4 = menu.add(0, PICK4, Menu.NONE, "Delete Entry");
        MenuItem item5 = menu.add(0, PICK5, Menu.NONE, "Update Entry");

        item1.setShortcut('1', 's');
        item2.setShortcut('2', 'c');
        item3.setShortcut('3', 'a');
        item4.setShortcut('4', 'd');
        item5.setShortcut('5', 'u');

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super. onOptionsItemSelected(item);
        int itemID = item.getItemId();  //get id of menu item picked

        switch (itemID) {
            case PICK1:

                //Save List
                try {

                    out = new OutputStreamWriter(openFileOutput(file, MODE_PRIVATE));
                } catch (IOException e) {
                }
                try {

                    for (int i = 0; i < data.size(); i++) {
                        out.write(data.get(i) + "\n");
                    }

                    out.close();

                } catch (IOException e1) {
                }

                enterdata.setText("");
                return true;

// Back to MainMenu
            case PICK2:
                try {

                    out = new OutputStreamWriter(openFileOutput(file, MODE_PRIVATE));
                } catch (IOException e) {
                }

                try {
                    for (int i = 0; i < data.size(); i++) {
                        out.write(data.get(i) + "\n");
                    }

                    out.close();

                } catch (IOException e1) {

                } catch (Exception e) {
                }
                enterdata.setText("");
                finish();
                return true;

            case PICK3:

                //Add Entry
                //capture user's input
                data.add(enterdata.getText().toString());
                //to add the initial number to the ListView
                number.add(data.size() + ". " + enterdata.getText().toString());
                //speak
                try {
                    String caputured = enterdata.getText().toString();
                } catch (Exception e) {
                }

                adapter.notifyDataSetChanged();

                enterdata.setText("");

                return true;


            case PICK4:
                //before delete, user choose what to delete, the choice will be reflected into the EditText
                data.remove(enterdata.getText().toString());
                //Simply map another ArrayList(with the initial number) to the modified ArrayList
                number.clear();
                for (int i = 0; i < data.size(); i++) {
                    number.add(i + 1 + ". " + data.get(i));
                }
                try {
                    String caputured = enterdata.getText().toString();
                } catch (Exception e) {
                }
                adapter.notifyDataSetChanged();

                enterdata.setText("");

                return true;


            case PICK5: //Update Entry

                //record the modified item location in the ArrayList, I need to change it in another ArrayList(with the initial number)
                int clickedItemIndex = data.indexOf(items);//cannot use et.getText().toString() for temp because the EditText will be updated right after user click the ListView item
                //modified it first in the first ArrayList, change the item to whatever user types new in the EditText
                //this new EditText has been modified!
                data.set(clickedItemIndex, enterdata.getText().toString());
                //new item is recorded
                number.set(clickedItemIndex, (clickedItemIndex + 1) + ". " + data.get(clickedItemIndex));
                adapter.notifyDataSetChanged();
                enterdata.setText("");
                return true;
        }

        return false;
    }


    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

        items = data.get(position);
        enterdata.setText(items);
    }

}
