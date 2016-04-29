package xuezhou_cs680.easytravel;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Kun on 4/28/16.
 */
public class ExpenseActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    EditText location;
    EditText date;
    EditText dollar_spent;
    ListView list;
    String listItems;
    String location_item = "";
    String date_item = "";
    String amt_item = "";
    String object="";
    private final String file = "list.txt";
    private FileOutputStream fos;
    private OutputStreamWriter out;
    private InputStream in;
    InputStream inputStream= null;
    private TextToSpeech speaker;
    private static final String tag = "Widgets";


    ArrayList<String> city = new ArrayList<String>();
    ArrayList<String> time = new ArrayList<String>();
    ArrayList<String> amt = new ArrayList<String>();

    final int PICK1 = Menu.FIRST + 1;
    final int PICK2 = Menu.FIRST + 2;
    final int PICK3 = Menu.FIRST + 3;
    final int PICK4 = Menu.FIRST + 4;
    final int PICK5 = Menu.FIRST + 5;
    final int PICK6 = Menu.FIRST + 6;
    final int PICK7 = Menu.FIRST + 7;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list_items);
        location = (EditText) findViewById(R.id.location);
        date = (EditText) findViewById(R.id.date);
        dollar_spent = (EditText) findViewById(R.id.dollar_spent);

        try {
            File file=new File(getFilesDir()+"/list.txt");
            if (file.exists()){
                inputStream = openFileInput("list.txt");
                InputStreamReader in=new InputStreamReader(inputStream);
                BufferedReader reader= new BufferedReader(in);
                String str="";
                while((str=reader.readLine())!=null){
                    city.add(str.substring(0,str.indexOf(";")));
                    time.add(str.substring(str.indexOf(";")+1,str.lastIndexOf(";")));
                    amt.add(str.substring(str.lastIndexOf(";")+1));
                }
                reader.close();
            }else{
                city.add("Toro");
                city.add("Gourmet");
                city.add("Lobster");
                city.add("BonChon");
                time.add("Mar-10");
                time.add("Mar-11");
                time.add("Mar-13");
                time.add("Mar-13");
                amt.add("47.89");
                amt.add("40.35");
                amt.add("34.56");
                amt.add("30.99");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //adapter

        Adapter adapter = new Adapter(this, city, time, amt);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                location_item = String.valueOf(city.get(position));
                date_item = String.valueOf(time.get(position));
                amt_item = String.valueOf(amt.get(position));
                location.setText(location_item);
                date.setText(date_item);
                dollar_spent.setText(amt_item);

            }
        });

        speaker = new TextToSpeech(this,this);  //Initialize Text to Speech engine (context, listener object)
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    public void speak(String output){
        //	speaker.speak(output, TextToSpeech.QUEUE_FLUSH, null);for apis before 21
        speaker.speak(output, TextToSpeech.QUEUE_FLUSH, null);
    }
    public void onInit(int status) {  // Implements TextToSpeech.OnInitListener.
        if(status == TextToSpeech.SUCCESS){   // status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
            int result=speaker.setLanguage(Locale.US); //set preferred language to speak

            if(result==TextToSpeech.LANG_MISSING_DATA || // If a language is not be available
                    result== TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e(tag,"Language is not available."); //log error info if language data is missing or the language is not supported.
            }else{
                speak("Please enter your expense information in the textbox.");//successfully initial TTS engine
                Log.i(tag,"TTS Initialization successful."); //log successful info
            }

        }else{
            Log.e(tag, "Could not initialize TextToSpeech."); //lof error info if initialization failed
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  //add menu items
        super.onCreateOptionsMenu(menu);

        MenuItem item1 = menu.add(0, PICK1, Menu.NONE, "Save");
        MenuItem item2 = menu.add(0, PICK2, Menu.NONE, "Finish");
        MenuItem item3 = menu.add(0, PICK3, Menu.NONE, "Add");
        MenuItem item4 = menu.add(0, PICK4, Menu.NONE, "Delete");
        MenuItem item5 = menu.add(0, PICK5, Menu.NONE, "Update");
        MenuItem item6 = menu.add(0, PICK6, Menu.NONE, "Expense Map");
        MenuItem item7 = menu.add(0, PICK7, Menu.NONE, "Expense Calculator");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int itemID = item.getItemId();
        switch (itemID) {
            case PICK1: //SAVE
                ((BaseAdapter) list.getAdapter()).notifyDataSetChanged();
                doThatIO();
                location.setText("");
                date.setText("");
                dollar_spent.setText("");
                return true;
            case PICK2://Close
                doThatIO();
                this.finish();

                location.setText("");
                date.setText("");
                dollar_spent.setText("");
                return true;
            case PICK3://add

                city.add(location.getText().toString().trim());
                time.add(date.getText().toString().trim());
                amt.add(dollar_spent.getText().toString().trim());

                try{
                    String text=dollar_spent.getText().toString().trim()+""+"dollars"+""+"at"+""+location.getText().toString().trim()+""+"on"+""+date.getText().toString().trim();
                    if(speaker.isSpeaking()){   //if speaker is working, stop speaker
                        speaker.stop();
                    }else{
                        speak("Expense of"+text+"is added");   //speak add item
                    }
                }catch (Exception e){}

                ((BaseAdapter) list.getAdapter()).notifyDataSetChanged();

                location.setText("");
                date.setText("");
                dollar_spent.setText("");
                return true;

            case PICK4://delete
                for (int i = 0; i < city.size(); i++) {
                    if (city.get(i).equals(location_item)) {
                        city.remove(i);
                        time.remove(i);
                        amt.remove(i);
                    }
                }
                try{
                    String text=dollar_spent.getText().toString().trim()+""+"dollars"+""+"at"+""+location.getText().toString().trim()+""+"on"+""+date.getText().toString().trim();
                    if(speaker.isSpeaking()){   //if speaker is working, stop speaker
                        speaker.stop();
                    }else{
                        speak("Expense of"+text+"is deleted");   //speak add item
                    }
                }catch (Exception e){}

                ((BaseAdapter) list.getAdapter()).notifyDataSetChanged();
                location.setText("");
                date.setText("");
                dollar_spent.setText("");
                return true;
            case PICK5://update
                int index = 0;

                for (int i = 0; i < city.size(); i++) {
                    if ((city.get(i).equals(location_item))) {
                        index = i;
                    }
                }

                city.set(index, location.getText().toString().trim());
                time.set(index, date.getText().toString().trim());
                amt.set(index, dollar_spent.getText().toString().trim());

                try{
                    if(speaker.isSpeaking()){   //if speaker is working, stop speaker
                        speaker.stop();
                    }else{
                        speak("Expense list updated.");   //speak add item
                    }

                }catch (Exception e){}
                ((BaseAdapter) list.getAdapter()).notifyDataSetChanged();
                location.setText("");
                date.setText("");
                dollar_spent.setText("");
                //if any change in amt occur, update the change

                return true;

            case PICK6: //open calculator activity
                this.startActivity(new Intent(this, ExpenseMapActivityk.class));
                return true;
            case PICK7: //open calculator activity
                this.startActivity(new Intent(this, CalculatorActivity.class));
                return true;

        }
        return false;
    }

    public void doThatIO(){
        ArrayList<String> append=new ArrayList<>();
        try{
            fos=openFileOutput("list.txt",MODE_PRIVATE);
            out=new OutputStreamWriter(fos);
        }catch (IOException e){

        }
        try{

            for(int i=0;i<city.size();i++){
                object="";
                object=city.get(i)+";"+time.get(i)+";"+amt.get(i);
                append.add(object);
                out.write(append.get(i).trim()+"\n");
            }
            out.close();
        }catch (IOException e){
            Log.e("list",e.getMessage());
        }
    }
    @Override
    public boolean onKeyUp(int keycode, KeyEvent event){   //
        super.onKeyUp(keycode, event);
        if (keycode == KeyEvent.KEYCODE_ENTER) {
            doThatIO();
            return true;
        }
        return true;
    }
}
