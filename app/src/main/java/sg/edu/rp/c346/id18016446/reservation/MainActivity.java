package sg.edu.rp.c346.id18016446.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    EditText textName;
    EditText textPhone;
    EditText textNo;
    DatePicker dp;
    TimePicker tp;
    CheckBox cb;
    Button btn;
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textName = findViewById(R.id.textName);
        textPhone = findViewById(R.id.textPhone);
        textNo = findViewById(R.id.textNo);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        cb = findViewById(R.id.checkBox);
        btn = findViewById(R.id.button);
        btnReset = findViewById(R.id.buttonReset);

        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);
        dp.updateDate(2020, 5, 1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String display = "";
                String name = String.valueOf(textName.getText());
                String phone = String.valueOf(textPhone.getText());
                String no = String.valueOf(textNo.getText());
                int hour = tp.getCurrentHour();
                int minute = tp.getCurrentMinute();
                int day = dp.getDayOfMonth();
                int month = dp.getMonth() + 1;
                int year = dp.getYear();

                if (name.isEmpty() || phone.isEmpty() || no.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                } else {
                    if (cb.isChecked()) {
                        display = String.format("Name: %s\nPhone: %s\nSize of the group: %s\nTime is %d:%d\nDate is %d/%d/%d\nArea: Smoking Area", name, phone, no, hour, minute, day, month, year);
                    } else {
                        display = String.format("Name: %s\nPhone: %s\nSize of the group: %s\nTime is %d:%d\n Date is %d/%d/%d\nArea: Non-Smoking Area", name, phone, no, hour, minute, day, month, year);
                    }

                    Toast.makeText(MainActivity.this, display, Toast.LENGTH_SHORT).show();
                }

            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                int hour = tp.getCurrentHour();
                if (hour < 8) {
                    tp.setCurrentHour(8);
                    tp.setCurrentMinute(0);
                    Toast.makeText(MainActivity.this, "Earliest Reservations can only be done at 8am.", Toast.LENGTH_SHORT).show();
                } else if (hour > 20){
                    tp.setCurrentHour(20);
                    tp.setCurrentMinute(59);
                    Toast.makeText(MainActivity.this, "Latest Reservation can only be done at 9pm.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textName.setText("");
                textPhone.setText("");
                textNo.setText("");
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                dp.updateDate(2020, 5, 1);
                cb.setChecked(false);
            }
        });
    }
}
