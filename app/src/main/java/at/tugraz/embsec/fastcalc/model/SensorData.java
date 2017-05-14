package at.tugraz.embsec.fastcalc.model;


import android.content.ContentValues;

public class SensorData {

    private Integer id;
    private Integer btn;

    private Float acc_x;
    private Float acc_y;
    private Float acc_z;

    private Float gyr_x;
    private Float gyr_y;
    private Float gyr_z;

    private Float lgt_a;
    private Float lgt_b;

    public SensorData(Integer btn, Float acc_x, Float acc_y, Float acc_z, Float gyr_x, Float gyr_y, Float gyr_z, Float lgt_a, Float lgt_b) {
        this.btn = btn;
        this.acc_x = acc_x;
        this.acc_y = acc_y;
        this.acc_z = acc_z;
        this.gyr_x = gyr_x;
        this.gyr_y = gyr_y;
        this.gyr_z = gyr_z;
        this.lgt_a = lgt_a;
        this.lgt_b = lgt_b;
    }

    public SensorData() {
        this.acc_x = null;
        this.acc_y = null;
        this.acc_z = null;
        this.gyr_x = null;
        this.gyr_y = null;
        this.gyr_z = null;
        this.lgt_a = null;
        this.lgt_b = null;
    }

    // copy constructor used for writing in new thread
    public SensorData(SensorData sd) {
        this.btn = sd.getBtn();
        this.acc_x = sd.getAcc_x();
        this.acc_y = sd.getAcc_y();
        this.acc_z = sd.getAcc_z();
        this.gyr_x = sd.getGyr_x();
        this.gyr_y = sd.getGyr_y();
        this.gyr_z = sd.getGyr_z();
        this.lgt_a = sd.getLgt_a();
        this.lgt_b = sd.getLgt_b();
    }

    public Integer getId() {
        return id;
    }

    public Integer getBtn() {
        return btn;
    }

    public Float getAcc_x() {
        return acc_x;
    }

    public Float getAcc_y() {
        return acc_y;
    }

    public Float getAcc_z() {
        return acc_z;
    }

    public Float getGyr_x() {
        return gyr_x;
    }

    public Float getGyr_y() {
        return gyr_y;
    }

    public Float getGyr_z() {
        return gyr_z;
    }

    public Float getLgt_a() {
        return lgt_a;
    }

    public Float getLgt_b() {
        return lgt_b;
    }

    public void setBtn(Integer btn) {
        this.btn = btn;
    }

    public void setAcc(Float acc_x, Float acc_y, Float acc_z) {
        this.acc_x = acc_x;
        this.acc_y = acc_y;
        this.acc_z = acc_z;
    }

    public void setGyr(Float gyr_x, Float gyr_y, Float gyr_z) {
        this.gyr_x = gyr_x;
        this.gyr_y = gyr_y;
        this.gyr_z = gyr_z;
    }

    public void setLgt(Float lgt_a, Float lgt_b) {
        this.lgt_a = lgt_a;
        this.lgt_b = lgt_b;
    }

    public boolean isReadyToCommit() {
        return (this.acc_x != null && this.acc_y != null && this.acc_z != null && this.gyr_x != null &&
                this.gyr_y != null && this.gyr_z != null && lgt_a != null && lgt_b != null);
    }

    @Deprecated
    public String getSqlInsertString() {
        return "INSERT INTO sensordata VALUES(NULL, " + this.btn + ", " + this.acc_x +
                ", " + this.acc_y + ", " + this.acc_z + ", " + this.gyr_x + ", " + this.gyr_y +
                ", " + this.gyr_z + ", " + this.lgt_a + ", " + this.lgt_b + ");";
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();

        cv.put("btn", this.btn);
        cv.put("acc_x", this.acc_x);
        cv.put("acc_y", this.acc_y);
        cv.put("acc_z", this.acc_z);
        cv.put("gyr_x", this.gyr_x);
        cv.put("gyr_y", this.gyr_y);
        cv.put("gyr_z", this.gyr_z);
        cv.put("lgt_a", this.lgt_a);
        cv.put("lgt_b", this.lgt_b);

        return cv;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.btn);
        sb.append("; ");
        sb.append((double)this.acc_x + "; " + (double)this.acc_y + "; " + (double)this.acc_z);
        sb.append("; ");
        sb.append((double)this.gyr_x + "; " + (double)this.gyr_y + "; " + (double)this.gyr_z);
        sb.append("; ");
        sb.append((double)this.lgt_a + "; " + (double)this.lgt_b);
        sb.append("\n");

        return sb.toString();
    }
}
