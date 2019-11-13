package com.example.aplikasikrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasikrs.Admin.HomeAdmin;
import com.example.aplikasikrs.Admin.Model.Dosen;
import com.example.aplikasikrs.Admin.Model.Mahasiswa;
import com.example.aplikasikrs.Mahasiswa.HomeDosen;

public class MainActivity extends AppCompatActivity {

    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        email = findViewById(R.id.emailtxt);


        Button btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(myBtnSignInClick);


    }
private  View.OnClickListener myBtnSignInClick = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
  SharedPreferences prefs = MainActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
  String statusLogin = prefs.getString("isLogin",null);
  SharedPreferences.Editor edit = prefs.edit();
  String emailKey = email.getText().toString();
  if (emailKey.contains("@staff.ukdw.ac.id")){
      edit.putString("isLogin","admin");
      edit.commit();
      Intent intent = new Intent(MainActivity.this,HomeAdmin.class);
      startActivity(intent);
  }
  else if (emailKey.contains("@si.ukdw.ac.id")){
      edit.putString("isLogin","mhs");
      edit.commit();
      Intent intent = new Intent(MainActivity.this,HomeDosen.class);
      startActivity(intent);
  }
  else {
      Toast.makeText(MainActivity.this,"Emailmu salah sek bo lebokke !!",
      Toast.LENGTH_SHORT).show();
  }

    }
};

    };

