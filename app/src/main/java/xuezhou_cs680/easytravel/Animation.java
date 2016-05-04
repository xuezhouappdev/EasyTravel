package xuezhou_cs680.easytravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

/* This code  provided by wensi tang, and using viewFlipper to implement animation.
 The viewFlipper has derived from ViewAnimator. It supports the methods to set the animation for the in and out actions.*/

public class Animation extends AppCompatActivity {

        float startposition;
        ViewFlipper viewFlipper = null;// set up viewFlipper object
        private GestureDetector gestureDetector = null;
        private Button bt ; // Add button on the last image to start MainActivity



        protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_animation);
        init();

         // Add button on the last image to start MainActivity
        bt=(Button)findViewById(R.id.button01);
        bt.setOnClickListener(new View.OnClickListener()
        {


        public void onClick(View v)
        {
                    /* control auto viewfipper ,set up interval time to 3000ms
                    viewFlipper.setAutoStart(true);
                    viewFlipper.setFlipInterval(3000);

                     if( viewFlipper.isAutoStart() && !viewFlipper.isFlipping())
                    {
                    viewFlipper.stopFlipping();
                    }*/

        // Click button to enter to next Main page
        Toast.makeText(getApplicationContext(), "Start your trip now", Toast.LENGTH_LONG).show();

        Intent intentanimation = new Intent(v.getContext(), MainActivity.class);
                startActivity(intentanimation);

        }
        });
        }

        private void init()
        {
        viewFlipper = (ViewFlipper) this.findViewById(R.id.viewf);
        // To identify viewflipper from layout page
        }


        // Moving imageview with touch event and doing Auto-generated method stub
        public boolean onTouchEvent(MotionEvent event)
        {
             switch (event.getAction())
        {
            // To get the animation method
             case MotionEvent.ACTION_DOWN:
             startposition = event.getX();
             break;

            case MotionEvent.ACTION_UP:

                if (event.getX() > startposition) //slide to right to get to next image
        {
                    viewFlipper.setInAnimation(this, R.anim.push_right_in);
                    viewFlipper.setOutAnimation(this, R.anim.push_right_out);
                    viewFlipper.showNext();


        } else if (event.getX() < startposition) //slide to left to get to next image
        {
                    viewFlipper.setInAnimation(this, R.anim.push_left_in);
                    viewFlipper.setOutAnimation(this, R.anim.push_left_out);
                    viewFlipper.showNext();
        } else break;
        }

        return super.onTouchEvent(event);
        }




        public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

        }
