package com.example.aplikasikrs.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aplikasikrs.Mahasiswa.Model.DefaultResult;
import com.example.aplikasikrs.Network.GetDataService;
import com.example.aplikasikrs.Network.RetrofitClientInstance;
import com.example.aplikasikrs.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateMhsActivity extends AppCompatActivity {
    private EditText nama_mahasiswa;
    private EditText alamat_mahasiswa;
    private EditText email_mahasiswa;
    private EditText nim_mahasiswa;
    private EditText foto_mahasiswa;
    private ImageView imgDosen;
    ;
    ProgressDialog progressDialog;
    boolean isUpdate = false;
    String nimMahasiswa = "";
    String part_image;
    private  String stringImg = "";
    GetDataService service;
    final int Request_Gallery = 9544;
    private static final int File_ACCESS_REQUEST_CODE = 58;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mhs);
        this.setTitle("SI KRS - Hai Mahasiswa");
        nama_mahasiswa = (EditText) findViewById(R.id.editText);
        nim_mahasiswa = (EditText) findViewById(R.id.editText2);
        alamat_mahasiswa = (EditText) findViewById(R.id.editText6);
        email_mahasiswa = (EditText) findViewById(R.id.editText5);
//        imgDosen = findViewById(R.id.imgDosen);

        Button btnCreateMhs = (Button)findViewById(R.id.btnCreateMhs);
        if (nama_mahasiswa.getText().toString().length()== 0 ){
            nama_mahasiswa.setError("Isi Nama Dosen");
        }

        if (nim_mahasiswa.getText().toString().length()==0) {
            nim_mahasiswa.setError("Isi NIDN Dosen");
        }

        if (alamat_mahasiswa.getText().toString().length()==0) {
            alamat_mahasiswa.setError("Isi Alamat Dosen");
        }
        if (email_mahasiswa.getText().toString().length()==0) {
            email_mahasiswa.setError("Isi Email Dosen");
        }
        if (isUpdate){
            btnCreateMhs.setText("Update");

        }

        btnCreateMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isUpdate) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateMhsActivity.this);
                    builder.setMessage("Mengubah Data?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CreateMhsActivity.this, "Tidak Menyimpan", Toast.LENGTH_SHORT).show();
                        }
                    })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
//                                    update_mahasiswa();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }

                Intent intent = new Intent(CreateMhsActivity.this, HomeAdmin.class);
                startActivity(intent);
            }
        });
    }
    void checkUpdate(){
        Bundle extras = getIntent().getExtras();
        if (extras == null){
            return;
        }
        isUpdate = extras.getBoolean("is_Update");
        nama_mahasiswa.setText(extras.getString("nama_dosen"));
        nim_mahasiswa.setText(extras.getString("nidn_dosen"));
        alamat_mahasiswa.setText(extras.getString("alamat_dosen"));
        email_mahasiswa.setText(extras.getString("email_dosen"));

    }


    }

