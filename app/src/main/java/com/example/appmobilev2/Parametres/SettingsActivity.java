package com.example.appmobilev2.Parametres;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.appmobilev2.Classes.CalendarJour;
import com.example.appmobilev2.DataBase.DataBaseManager;
import com.example.appmobilev2.QRCode.QrCodeActivity;
import com.example.appmobilev2.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        private androidx.preference.Preference scanQRCode;
        private androidx.preference.EditTextPreference URLSYNC;
        private DataBaseManager db;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            db = new DataBaseManager(this.getContext());
            scanQRCode = findPreference("SCANQRCODE");
            URLSYNC = findPreference("URLSYNC");
            URLSYNC.setText("");
            scanQRCode.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(new Intent(preference.getContext(), QrCodeActivity.class));
                    return true;
                }
            });
            URLSYNC.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if(URLSYNC.getText() != null)
                        db.insertLien(newValue.toString());
                    Intent Calendar = new Intent(preference.getContext(), CalendarJour.class);
                    Calendar.putExtra("LienEDT", newValue.toString());
                    startActivity(Calendar);
                    return true;
                }
            });
        }
    }
}