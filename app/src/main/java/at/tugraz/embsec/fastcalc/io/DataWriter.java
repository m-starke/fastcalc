package at.tugraz.embsec.fastcalc.io;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import at.tugraz.embsec.fastcalc.db.SensorData;


public class DataWriter implements Runnable {

    Context context;

    String filepath;
    SensorData sensor_data;

    public DataWriter(Context context, String filename, SensorData data) {
        this.context = context;
        this.filepath = (new File(Environment.getDataDirectory(), filename)).getAbsolutePath();
        this.sensor_data = data;
    }

    @Override
    public void run() {

        FileWriter fw;

        try {
            fw = new FileWriter(this.filepath, true); // yes, append to existing file
            fw.append(this.sensor_data.toString());
            fw.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(this.context, e.getMessage(), Toast.LENGTH_LONG).show();
            return;
        } catch (IOException e) {
            Toast.makeText(this.context, e.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }

    }

    public static void clearFile(Context context, String filename) {
        try {
            FileWriter fw = new FileWriter((new File(Environment.getDataDirectory(), filename)).getAbsolutePath(), false);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
