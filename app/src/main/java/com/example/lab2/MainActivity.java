package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickChangeBackground(View view) {
        Random random = new Random();
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        View parent = (View) view.getParent();
        parent.setBackgroundColor(color);
    }

    public void textToSpeech(View view) {
        Intent intent = new Intent(this, SpeakActivity.class);
        startActivity(intent);
    }

    public void showApiVersion(View view) {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        int version = Build.VERSION.SDK_INT;
        String versionRelease = Build.VERSION.RELEASE;

        String messageText = "manufacturer: " + manufacturer
                + "\nmodel: " + model
                + "\nversion: " + version
                + "\nversionRelease: " + versionRelease;
        Toast.makeText(this, messageText, Toast.LENGTH_LONG).show();
    }

    public void shareSerialNumber(View view) {
        String deviceId = Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, deviceId);
        intent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(intent, null);
        startActivity(shareIntent);
    }
}