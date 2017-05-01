package at.tugraz.embsec.fastcalc;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button[] btn = new Button[11];
    private int[] btn_id = { R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDelete };

    private int current_s1;
    private int current_s2;
    private int current_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // do not allow landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // initialize grid layout buttons
        for (int i = 0; i < btn.length; i++) {
            btn[i] = (Button) findViewById(btn_id[i]);
            btn[i].setOnClickListener(new DigitInputListener(getApplicationContext(), findViewById(android.R.id.content)));
        }

        createEquation();
    }

    public void createEquation() {
        Random r = new Random();

        this.current_s1 = r.nextInt(257);
        this.current_s2 = r.nextInt(257);

        this.current_result = this.current_s1 + this.current_s2;

        ((TextView) findViewById(R.id.tvFirstSummand)).setText(String.valueOf(this.current_s1));
        ((TextView) findViewById(R.id.tvResult)).setText(String.valueOf(this.current_result));

        ((TextView) findViewById(R.id.tvSecondSummand)).setText("");
    }

    public boolean checkEquation() throws NumberFormatException {
        int summand = Integer.parseInt(((TextView) findViewById(R.id.tvSecondSummand)).getText().toString());

        if (summand == this.current_s2) {
            return true;
        } else {
            return false;
        }
    }

    public void onCheckClick(View view) {
        String correct = "Correct!";
        String wrong = "Wrong!";
        boolean valid = true;
        Toast t = null;

        try {
            // check if equation was correct
            valid = checkEquation();
        } catch (NumberFormatException e) {
            t = Toast.makeText(getApplicationContext(), "Please enter a numer!", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP, 0, 125);
            t.getView().setBackgroundColor(Color.rgb(255, 50, 50));
            t.show();
            return;
        }

        if (valid == true) {
            t = Toast.makeText(getApplicationContext(), correct, Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP, 0, 125);
            t.getView().setBackgroundColor(Color.rgb(75, 150, 0));
        } else {
            t = Toast.makeText(getApplicationContext(), wrong, Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP, 0, 125);
            t.getView().setBackgroundColor(Color.rgb(255, 50, 50));
        }

        t.show();

        // create new equation
        createEquation();
    }
}
