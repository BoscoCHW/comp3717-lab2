package com.example.lab2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SpeakActivity extends AppCompatActivity {

    TextToSpeechService textToSpeechService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speak_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, TextToSpeechService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        Button speakButton = findViewById(R.id.speechSpeakBtn);
        speakButton.setOnClickListener(view -> {
            EditText editText = findViewById(R.id.edit_message);
            String text = editText.getText().toString().trim();
            if (textToSpeechService != null) {
                textToSpeechService.speak(text);
            }
        });

        Button backButton = findViewById(R.id.speechBackBtn);
        backButton.setOnClickListener(view -> finish());
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            TextToSpeechService.LocalBinder binder = (TextToSpeechService.LocalBinder) iBinder;
            textToSpeechService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            textToSpeechService = null;
        }
    };
}
