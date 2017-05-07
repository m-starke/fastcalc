package at.tugraz.embsec.fastcalc;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class InputListener extends View implements View.OnClickListener, SensorEventListener {

    private View main_view;
    private SensorManager sm;

    public InputListener(Context context, View main, AppCompatActivity activity) {
        super(context);
        this.main_view = main;
        this.sm = (SensorManager) activity.getSystemService(activity.SENSOR_SERVICE);
    }

    @Override
    public void onClick(View v) {

        TextView input = (TextView) this.main_view.findViewById(R.id.tvSecondSummand);

        if (input.getText().length() > 4 && v.getId() != R.id.btnDelete) {
            // maybe also collect training data here, but is it necessary?
            return;
        }

        // collect training data here ...
        switch (v.getId()) {
            case R.id.btn0:
                input.setText(input.getText() + "0");
                break;
            case R.id.btn1:
                input.setText(input.getText() + "1");
                break;
            case R.id.btn2:
                input.setText(input.getText() + "2");
                break;
            case R.id.btn3:
                input.setText(input.getText() + "3");
                break;
            case R.id.btn4:
                input.setText(input.getText() + "4");
                break;
            case R.id.btn5:
                input.setText(input.getText() + "5");
                break;
            case R.id.btn6:
                input.setText(input.getText() + "6");
                break;
            case R.id.btn7:
                input.setText(input.getText() + "7");
                break;
            case R.id.btn8:
                input.setText(input.getText() + "8");
                break;
            case R.id.btn9:
                input.setText(input.getText() + "9");
                break;
            case R.id.btnDelete:
                if (input.getText().length() > 0) {
                    input.setText(input.getText().subSequence(0, input.getText().length() - 1));
                }
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }


}
