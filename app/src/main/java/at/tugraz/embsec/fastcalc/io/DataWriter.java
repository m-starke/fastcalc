package at.tugraz.embsec.fastcalc.io;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import at.tugraz.embsec.fastcalc.model.SensorData;


public class DataWriter implements Runnable {

    Context context;

    String filepath;
    SensorData sensor_data;

    public static File DIRECTORY = Environment.getExternalStorageDirectory();


    public DataWriter(Context context, String filename, SensorData data) {
        this.context = context;
        this.filepath = (new File(DataWriter.DIRECTORY, filename)).getAbsolutePath();
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

        //Log.d("DataWriter", Thread.currentThread().getId() + ": Wrote SensorData to file!");
    }

    public static synchronized void clearFile(String filename) throws IOException {
        FileWriter fw = new FileWriter((new File(DataWriter.DIRECTORY, filename)).getAbsolutePath(), false); // do not append, replace
        fw.write("");
        fw.close();
    }

    public static synchronized int getRowCount(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(DataWriter.DIRECTORY, filename)));
        int lines = 0;
        while (reader.readLine() != null) {
            lines++;
        }
        reader.close();
        return lines;
    }
}
