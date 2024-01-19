package com.weatherhub.WeatherHub.config;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseConfig {

    public static void initializeFirebaseApp() {
        try {
            // Firebase yapılandırma dosyasının yolu
            String firebaseConfigPath = "path/to/firebase-admin-sdk.json";

            // Firebase uygulamasını başlatmak için gerekli yapılandırma
            FileInputStream serviceAccount = new FileInputStream(firebaseConfigPath);
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();

            // Firebase uygulamasını başlat
            FirebaseApp.initializeApp(options);
            System.out.println("Firebase initialized successfully.");
        } catch (IOException e) {
            System.err.println("Firebase initialization failed: " + e.getMessage());
        }
    }

}
