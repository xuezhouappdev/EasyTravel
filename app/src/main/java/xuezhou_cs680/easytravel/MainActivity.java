package xuezhou_cs680.easytravel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView img=null;
    private MenuInflater menuInflater;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        img = (ImageView)findViewById(R.id.imageView);
        img.setImageResource(R.drawable.earth);


        btn1 = (Button)findViewById(R.id.imageButton);
        btn2 = (Button)findViewById(R.id.imageButton2);
        btn3 = (Button)findViewById(R.id.imageButton3);
        btn4 = (Button)findViewById(R.id.imageButton4);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //ActionBar actionBar =getActionBar();
        menuInflater = getMenuInflater();



        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intentCalculator = new Intent(v.getContext(),ExpenseActivity.class);
                startActivity(intentCalculator);
            }
        });





        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Uri uri;
                uri = Uri.parse("geo:<lat>,<long>?z=10");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);}


            }


        });





        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri;

                uri = Uri.parse("https://weather.com/weather/today/l/USMA0046:1:US");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });



        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCalculator = new Intent(v.getContext(),ExpenseActivity.class);
                startActivity(intentCalculator);
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Uri uri;
        // Handle item selection
        switch (item.getItemId()) {
            /*case R.id.web:
                uri = Uri.parse("http://www.bentley.edu");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
                return true;

            case R.id.maps:
                uri = Uri.parse("geo:0,0?q=restaurants");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
	        	*//*For API18 and 19 Google Maps is not on Launch Pad
             	 so should first check if Package is present to avoid app crashing.
             	 The API 23 emulator using the Intel instruction set would also crash here
             	 without first checking.
            	*//*
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);}
                return true;*/

            case R.id.dialer:
                uri = Uri.parse("tel:8574004109");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
                return true;

            case R.id.feedback:
                uri = Uri.parse("mailto:xuezhou9019@gmail.com");
                startActivity(new Intent(Intent.ACTION_SENDTO, uri));
                return true;

            case R.id.about:
                Intent intenthelp = new Intent(this,Helpdocument.class);
                   startActivity(intenthelp);


                return true;

            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }













}//class ends
