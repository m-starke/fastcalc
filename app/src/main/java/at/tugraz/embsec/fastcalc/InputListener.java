package at.tugraz.embsec.fastcalc;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import at.tugraz.embsec.fastcalc.db.DatabaseHelper;
import at.tugraz.embsec.fastcalc.db.SensorData;


public class InputListener extends View implements View.OnClickListener, SensorEventListener {

    private View main_view;
    private Context main_context;
    private MainActivity main_activity;
    private SensorManager sm;

    private static int MAX_DIGITS = 4;

    public static SensorData SENSORDATA;

    private static DatabaseHelper DATABASE;

    private static String DB_RESET_CODE = "3333";
    private static String DB_LAST_ROW_CODE = "1337";
    private static String DB_ROW_COUNT_CODE = "1338";

    public InputListener(Context context, View main, AppCompatActivity activity) {
        super(context);
        this.main_view = main;
        this.main_context = context;
        this.main_activity = (MainActivity) activity;
        this.sm = (SensorManager) activity.getSystemService(activity.SENSOR_SERVICE);

        if (InputListener.SENSORDATA == null) {
            InputListener.SENSORDATA = new SensorData();
        }

        if (InputListener.DATABASE == null) {
            InputListener.DATABASE = new DatabaseHelper(context);
        }
    }

    @Override
    public void onClick(View v) {

        TextView input = (TextView) this.main_view.findViewById(R.id.tvSecondSummand);

        if (input.getText().length() > InputListener.MAX_DIGITS && v.getId() != R.id.btnDelete) {
            return;
        }

        if (InputListener.SENSORDATA == null) {
            InputListener.SENSORDATA = new SensorData();
        }

        switch (v.getId()) {
            case R.id.btn0:
                input.setText(input.getText() + "0");
                InputListener.SENSORDATA.setBtn(0);
                break;
            case R.id.btn1:
                input.setText(input.getText() + "1");
                InputListener.SENSORDATA.setBtn(1);
                break;
            case R.id.btn2:
                input.setText(input.getText() + "2");
                InputListener.SENSORDATA.setBtn(2);
                break;
            case R.id.btn3:
                input.setText(input.getText() + "3");
                InputListener.SENSORDATA.setBtn(3);
                break;
            case R.id.btn4:
                input.setText(input.getText() + "4");
                InputListener.SENSORDATA.setBtn(4);
                break;
            case R.id.btn5:
                input.setText(input.getText() + "5");
                InputListener.SENSORDATA.setBtn(5);
                break;
            case R.id.btn6:
                input.setText(input.getText() + "6");
                InputListener.SENSORDATA.setBtn(6);
                break;
            case R.id.btn7:
                input.setText(input.getText() + "7");
                InputListener.SENSORDATA.setBtn(7);
                break;
            case R.id.btn8:
                input.setText(input.getText() + "8");
                InputListener.SENSORDATA.setBtn(8);
                break;
            case R.id.btn9:
                input.setText(input.getText() + "9");
                InputListener.SENSORDATA.setBtn(9);
                break;
            case R.id.btnDelete:
                if (input.getText().length() > 0) {
                    if (input.getText().equals(InputListener.DB_RESET_CODE)) { // reset database
                        InputListener.DATABASE.resetDatabase();
                        Toast t = Toast.makeText(this.main_context, "Database was cleared!", Toast.LENGTH_LONG);
                        t.setGravity(Gravity.TOP, 0, 125);
                        t.getView().setBackgroundColor(Color.rgb(178, 102, 255));
                        t.show();
                        this.main_activity.createEquation();
                        return;
                    } else if (input.getText().equals(InputListener.DB_LAST_ROW_CODE)) { // information about last row
                        String entry = InputListener.DATABASE.getLastEntry();
                        Toast t = Toast.makeText(this.main_context, entry, Toast.LENGTH_LONG);
                        t.setGravity(Gravity.TOP, 0, 125);
                        t.getView().setBackgroundColor(Color.rgb(178, 102, 255));
                        t.show();
                        this.main_activity.createEquation();
                        return;
                    } else if (input.getText().equals(InputListener.DB_ROW_COUNT_CODE)) { // information about row count
                        String rowcount = "Rows in database: " + InputListener.DATABASE.getRowCount();
                        Toast t = Toast.makeText(this.main_context, rowcount, Toast.LENGTH_LONG);
                        t.setGravity(Gravity.TOP, 0, 125);
                        t.getView().setBackgroundColor(Color.rgb(178, 102, 255));
                        t.show();
                        this.main_activity.createEquation();
                        return;
                    }

                    input.setText(input.getText().subSequence(0, input.getText().length() - 1));
                }
                return; // not necessary to attach sensor listener
        }

        // register sensor listener only when necessary
        this.sm.registerListener(this, this.sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
        this.sm.registerListener(this, this.sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_FASTEST);
        this.sm.registerListener(this, this.sm.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_FASTEST);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        switch(event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER: // x, y, z
                ((TextView) this.main_view.findViewById(R.id.tv_accdata)).setText("" + event.values[0]);

                InputListener.SENSORDATA.setAcc(event.values[0], event.values[1], event.values[2]);

                this.sm.unregisterListener(this, this.sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
                break;
            case Sensor.TYPE_GYROSCOPE: // x, y, z
                ((TextView) this.main_view.findViewById(R.id.tv_gyrodata)).setText("" + event.values[0]);

                InputListener.SENSORDATA.setGyr(event.values[0], event.values[1], event.values[2]);

                this.sm.unregisterListener(this, this.sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
                break;
            case Sensor.TYPE_LIGHT: // ?, ?, 0.0
                ((TextView) this.main_view.findViewById(R.id.tv_lightdata)).setText("" + event.values[0]);

                InputListener.SENSORDATA.setLgt(event.values[0], event.values[1]);

                this.sm.unregisterListener(this, this.sm.getDefaultSensor(Sensor.TYPE_LIGHT));
                break;
        }

        // check if SensorData is fully populated and write to database
        if (InputListener.SENSORDATA.isReadyToCommit()) {

            InputListener.DATABASE.storeSensorData(InputListener.SENSORDATA);
            Log.d("InputListener", "Wrote SensorData to database!");
            InputListener.SENSORDATA = new SensorData();
        }
    }


}
