package com.example.appmobilev2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.appmobilev2.Classes.CalendarJour;
import com.example.appmobilev2.Parametres.SettingsActivity;
import com.example.appmobilev2.Rappels.Rappels_Affichage;

public class MainActivity extends AppCompatActivity {

    private TextView txt;
    private Button btn, btn_param, btn_rappels;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_main);

        this.txt = findViewById(R.id.textView);
        this.btn = findViewById(R.id.button_edt);
        this.btn_rappels = findViewById(R.id.button_rappels);
        btn_param = findViewById(R.id.button_param);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CalendarJourActivity = new Intent(getApplicationContext(), CalendarJour.class);
                startActivity(CalendarJourActivity);
            }
        });
        btn_param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ParametresActivity= new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(ParametresActivity);
            }
        });
        btn_rappels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RappelsActivity= new Intent(getApplicationContext(), Rappels_Affichage.class);
                startActivity(RappelsActivity);
            }
        });
    }

}