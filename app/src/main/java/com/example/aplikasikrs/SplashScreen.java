package com.example.aplikasikrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.TextView;

import com.example.aplikasikrs.Admin.Model.Dosen;
import com.example.aplikasikrs.Admin.Model.Mahasiswa;

public class SplashScreen extends AppCompatActivity {

    TextView tvSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);

        tvSplash = (TextView) findViewById(R.id.textView2);





        SharedPreferences prefs = SplashScreen.this.getSharedPreferences("prefe_file",MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin",null);
        if(statusLogin != null){
            if (statusLogin.equals("admin")){
                Intent intent = new Intent(SplashScreen.this, Dosen.class);
                startActivity(intent);
            }
            else if (statusLogin.equals("mhs")){
                Intent intent = new Intent(SplashScreen.this, Mahasiswa.class);
                startActivity(intent);
            }
        }else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }, 900L);
        }

    }
}
