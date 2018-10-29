package com.example.yura.mydisplaybrightness;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    float BackLightValue = 0.5f; // значение по умолчанию
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar BackLightControl = (SeekBar)findViewById(R.id.backlightcontrol);
        final TextView BackLightSetting = (TextView)findViewById(R.id.backlightsetting);
        Button UpdateSystemSetting = (Button)findViewById(R.id.updatesystemsetting);

        UpdateSystemSetting.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                int SysBackLightValue = (int)(BackLightValue * 255);

                //Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, SysBackLightValue);

                android.provider.Settings.System.putInt(getContentResolver(),
                        android.provider.Settings.System.SCREEN_BRIGHTNESS,
                        SysBackLightValue);

            }});

        BackLightControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                // TODO Auto-generated method stub
                BackLightValue = (float)arg1/100;
                BackLightSetting.setText(String.valueOf(BackLightValue*255));

                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.screenBrightness = BackLightValue;
                getWindow().setAttributes(layoutParams);
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
            }
        });
    }




    public void OnClickButton(View view) {
        TextView mInfoTextView = (TextView) findViewById(R.id.infoView);
        try {
            int curBrightnessValue = android.provider.Settings.System.getInt(
                    getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS);
            mInfoTextView.setText("Текущая яркость экрана: " + curBrightnessValue);
        } catch (Settings.SettingNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
