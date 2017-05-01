package at.tugraz.embsec.fastcalc;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


public class DigitInputListener extends View implements View.OnClickListener {

    private View main_view;

    public DigitInputListener(Context context, View main) {
        super(context);
        this.main_view = main;
    }

    @Override
    public void onClick(View v) {

        TextView input = (TextView) this.main_view.findViewById(R.id.tvSecondSummand);

        if (input.getText().length() > 4 && v.getId() != R.id.btnDelete) {
            return;
        }

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
}
