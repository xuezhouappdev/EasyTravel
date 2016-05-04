package xuezhou_cs680.easytravel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by Kun on 4/29/16.
 */
public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView breakfast,lunch,dinner,other,showtotal;
    private Button btn_calculate;
    DecimalFormat precision=new DecimalFormat("0.00"); //declare the decimal format
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expensecalculator_layout_expk);

        breakfast = (TextView) findViewById(R.id.breakfast_edittext);
        lunch = (TextView) findViewById(R.id.lunch_edittext);
        dinner = (TextView) findViewById(R.id.dinner_edittext);
        other = (TextView) findViewById(R.id.other_edittext);

        showtotal = (TextView) findViewById(R.id.showtotal);
        btn_calculate = (Button) findViewById(R.id.btn_calculate);

        btn_calculate.setOnClickListener(this);} //button click

    public void onClick(View view) {  //if the button is clicked, get the text in the edittext box
        String bre = breakfast.getText().toString();
        String lun = lunch.getText().toString();
        String din = dinner.getText().toString();
        String oth = other.getText().toString();
        //ParseFloat converts the string s to a floating-point number
        float totalexpense = Float.parseFloat(bre) + Float.parseFloat(lun) + Float.parseFloat(din) + Float.parseFloat(oth);

        showtotal.setText(precision.format(totalexpense));

    }
}
