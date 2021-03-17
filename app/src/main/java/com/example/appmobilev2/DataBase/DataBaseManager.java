package com.example.appmobilev2.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appmobilev2.Classes.Cours;
import com.example.appmobilev2.Rappels.Rappel;

import java.util.ArrayList;

public class DataBaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Base";
    private static final int DATABASE_VERSION = 1;

    public DataBaseManager(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSQL = "CREATE TABLE T_Cours ("
                + "   idCours integer primary key autoincrement,"
                + "   prof String,"
                + "   nomCours String,"
                + "   location String,"
                + "   heureDebutCours String,"
                + "   heureFinCours String,"
                + "   dateCours String"
                + ")";
        String strSQL2= "CREATE TABLE T_Lien ("
                + "   idLien integer primary key autoincrement,"
                + "   lien String"
                + ")";
        String strSQL3= "CREATE TABLE T_Rappel ("
                + "   idRappel integer primary key autoincrement,"
                + "   Titre String,"
                + "   Description String,"
                + "   Date String"
                + ")";
        db.execSQL(strSQL);
        db.execSQL(strSQL2);
        db.execSQL(strSQL3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertCours(String prof, String nom, String loc, String heureDeb, String heureFin, String date) {
        nom = nom.replace("'", "''");
        String strSql = "insert into T_Cours (prof, nomCours,location,heureDebutCours,heureFinCours,dateCours) VALUES ('"
                + prof + "', '" + nom + "', '" + loc + "', '" + heureDeb + "', '" + heureFin + "', '" + date + "')";
        this.getWritableDatabase().execSQL(strSql);
    }

    public void insertLien(String lien) {
        String strSql = "insert into T_Lien (lien) VALUES ('" + lien + "')";
        this.getWritableDatabase().execSQL(strSql);
    }

    public void insertRappel(String titre, String description, String date) {
        String strSql = "insert into T_Rappel (Titre, Description, Date) VALUES ('" + titre + "','" + description + "','" + date + "')";
        this.getWritableDatabase().execSQL(strSql);
    }

    public void delete() {
        String strSql = "DELETE FROM T_Cours";
        String strSql2 = "DELETE FROM T_Lien";
        String strSql3 = "DELETE FROM T_Rappel";
        this.getWritableDatabase().execSQL(strSql);
        this.getWritableDatabase().execSQL(strSql2);
        this.getWritableDatabase().execSQL(strSql3);
    }

    public ArrayList<Cours> getCours() {
        ArrayList<Cours> cours = new ArrayList<>();

        String strSql = "SELECT * FROM T_Cours";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while(! cursor.isAfterLast()) {
            Cours cour = new Cours(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
            cours.add(cour);
            cursor.moveToNext();
        }
        cursor.close();

        return cours;
    }

    public ArrayList<Rappel> getRappels() {
        ArrayList<Rappel> rappels = new ArrayList<>();
        String strSql = "SELECT * FROM T_Rappel";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while(! cursor.isAfterLast()) {
            Rappel rappel = new Rappel(cursor.getString(1), cursor.getString(2), cursor.getString(3));
            rappels.add(rappel);
            cursor.moveToNext();
        }
        cursor.close();

        return rappels;
    }
     public void SuppRappel(String titre){
        String strSQL = "DELETE FROM T_Rappel WHERE Titre ='" + titre + "'";
         this.getWritableDatabase().execSQL(strSQL);
     }
    public boolean isEmpty() {
        String strSql = "SELECT * FROM T_Lien";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0 || cursor == null) {
            return true;
        } else return false;
    }
}
