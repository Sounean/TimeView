package com.example.timblocker;

import static java.security.AccessController.getContext;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText dataEditText;
    private EditText timeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataEditText = findViewById(R.id.setting_data);
        timeEditText = findViewById(R.id.setting_time);
    }

    public void dataChange(View view) {
        //得到Calendar实例，可以通过这个实例得到当前年月日时分秒
        Calendar c = Calendar.getInstance();
        DatePickerDialog datagramPacket = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener(){
                    //在弹出DatePickerDialog中，点击确定后会触发onDateSet方法
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        //月份是从0开始，所以+1
                        String select_month = String.valueOf(month+1);
                        String select_day = String.valueOf(day);
                        //月份、日小于10，前面加一个0保持2位数显示
                        if((month+1)<10){
                            select_month = "0"+(month+1);
                        }
                        if(day<10){
                            select_day = "0"+day;
                        }
                        //把选择的日期填写到布局中EditText中
                        dataEditText.setText(year+"年"+select_month+"月"+select_day+"日");
                    }
                },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
        datagramPacket.show();
    }

    public void timeChange(View view) {
        Calendar c = Calendar.getInstance();
        new TimePickerDialog(this,AlertDialog.THEME_HOLO_LIGHT,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String select_hour = String.valueOf(hourOfDay);
                String select_minute = String.valueOf(minute);
                if(hourOfDay<10){
                    select_hour = "0"+hourOfDay;
                }
                if(minute<10){
                    select_minute = "0"+minute;
                }
                timeEditText.setText(select_hour+":"+select_minute);
            }
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
    }
}