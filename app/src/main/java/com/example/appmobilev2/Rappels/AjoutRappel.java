package com.example.appmobilev2.Rappels;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appmobilev2.DataBase.DataBaseManager;
import com.example.appmobilev2.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class AjoutRappel extends AppCompatActivity {

    private EditText titre, description, date;
    private Button button;
    private DataBaseManager db;
    private String vTitre, vDescription, vDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_rappel);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        db = new DataBaseManager(this);
        titre = findViewById(R.id.reg_title);
        description = findViewById(R.id.reg_descrip);
        date = findViewById(R.id.reg_date);
        button = findViewById(R.id.btn_ajout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vTitre = titre.getText().toString();
                vDescription = description.getText().toString();
                vDate = date.getText().toString();
                if(vTitre.isEmpty())
                    titre.setError("Champ vide");
                else if(vDescription.isEmpty())
                    description.setError("Champ vide");
                else if (vDate.isEmpty())
                    date.setError("Champ vide");
                else if (!checkValid(vDate))
                    date.setError("Mauvais format");
                else if (checkRightDate(vDate))
                    date.setError("La date est inférieure à la date du jour");
                else {
                    db.insertRappel(vTitre, vDescription, vDate);
                    db.close();
                    Intent RappelsActivity = new Intent(getApplicationContext(), Rappels_Affichage.class);
                    startActivity(RappelsActivity);
                    finish();
                }
            }
        });
    }

    private boolean checkValid(String dateD){
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.parse(dateD);
        } catch (ParseException e){
            return false;
        }
        return true;
    }
    private boolean checkRightDate(String dateD){
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        String todayDate = format.format(today);
        try {
            Date dateSelec  = format.parse(dateD);
            Date dateToday = format.parse(todayDate);
            int i = dateSelec.compareTo(dateToday);
            if (i < 0){
                return true;
            }
        } catch (ParseException e){
            return false;
        }
        return false;
    }
}