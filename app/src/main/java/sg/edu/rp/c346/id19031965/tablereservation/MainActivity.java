package sg.edu.rp.c346.id19031965.tablereservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    Button btnSumbit;
    Button btnReset;
    EditText etNumber;
    EditText etSize;
    CheckBox cb;
    TimePicker tp;
    DatePicker dp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editName);
        etNumber = findViewById(R.id.number);
        etSize = findViewById(R.id.groupSize);
        btnReset = findViewById(R.id.reset);
        btnSumbit = findViewById(R.id.btnSubmit);
        cb = findViewById(R.id.smoking);
        tp = findViewById(R.id.time);
        dp = findViewById(R.id.date);

        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String size = etSize.getText().toString();
                String mobile = etNumber.getText().toString();

                if(name.isEmpty() || size.isEmpty() || mobile.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter all required information", Toast.LENGTH_LONG).show();
                    return;
                }

                Calendar now = Calendar.getInstance();
                Calendar res = Calendar.getInstance();
                res.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), tp.getCurrentHour(), tp.getCurrentMinute());

                if(now.after(res)){
                    Toast.makeText(MainActivity.this, "Please select a date and time after today.", Toast.LENGTH_LONG).show();
                    return;
                }
                String smoke;
                if(cb.isChecked()){
                    smoke = "Smoking";
                }
                else{
                    smoke = "Non-Smoking";
                }

                Calendar df = Calendar.getInstance();
                String date = DateFormat.getDateInstance(DateFormat.FULL).format(df.getTime());

                Toast.makeText(MainActivity.this, "Hello " + name +
                                ", you have booked a table for " + size + " people/person seated at the "
                                + smoke + " table on " + date + ". Your mobile number is " + mobile + ".",
                        Toast.LENGTH_LONG).show();

            }

        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText(null);
                etNumber.setText(null);
                etSize.setText(null);
                cb.setChecked(false);
                tp.setCurrentHour(20);
                tp.setCurrentMinute(30);
                dp.updateDate(2020, 5, 1);
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay < 8){
                    Toast.makeText(MainActivity.this, "We open at 8am", Toast.LENGTH_LONG).show();
                    tp.setCurrentHour(8);
                }
                else if(hourOfDay >= 21){
                    Toast.makeText(MainActivity.this, "We have closed at 9pm", Toast.LENGTH_LONG).show();
                    tp.setCurrentHour(20);
                }

            }
        });

    }
}