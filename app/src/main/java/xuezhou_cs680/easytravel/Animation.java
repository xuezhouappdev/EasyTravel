package xuezhou_cs680.easytravel;



//  this code  provided by wensi tang, we using viewFlipper to implement animation



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;



public class Animation extends AppCompatActivity {

    float startposition;
    ViewFlipper viewFlipper = null;
    private GestureDetector gestureDetector = null;
    private Button bt ;



        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_animation);
        init();

        // toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        bt=(Button)findViewById(R.id.button01);
            bt.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {

                    viewFlipper.setAutoStart(true);
                    viewFlipper.setFlipInterval(3000);

                if(viewFlipper.isAutoStart() && !viewFlipper.isFlipping()){
                    viewFlipper.startFlipping();
                }

                Toast.makeText(getApplicationContext(), "Start your trip now", Toast.LENGTH_LONG).show();

            Intent intentanimation = new Intent(v.getContext(), MainActivity.class);
                startActivity(intentanimation);

            }

            });

        }


    private void init() {

        viewFlipper = (ViewFlipper) this.findViewById(R.id.viewf);
    }

    public boolean TODown(MotionEvent arg0) {
        return false;
    }


           public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startposition = event.getX();

                break;

               case MotionEvent.ACTION_UP:

                if (event.getX() > startposition) {
                    viewFlipper.setInAnimation(this, R.anim.push_right_in);
                    viewFlipper.setOutAnimation(this, R.anim.push_right_out);
                    viewFlipper.showNext();


                } else if (event.getX() < startposition) {
                    viewFlipper.setInAnimation(this, R.anim.push_left_in);
                    viewFlipper.setOutAnimation(this, R.anim.push_left_out);
                    viewFlipper.showNext();
                }
              else  break;
        }

        return super.onTouchEvent(event);
    }


    public void onShowPress(MotionEvent e) {
    }

    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }





}
