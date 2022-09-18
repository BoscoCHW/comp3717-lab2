package com.example.lab2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;

import androidx.annotation.Nullable;

import java.util.Locale;

public class TextToSpeechService extends Service {
    private TextToSpeech textToSpeech;

    private IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        TextToSpeechService getService() {
            return TextToSpeechService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        textToSpeech = new TextToSpeech(getApplicationContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(Locale.CANADA);
            }
        });
        return binder;
    }


    public void speak(String message) {
        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
    }

}

