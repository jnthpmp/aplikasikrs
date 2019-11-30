package com.example.aplikasikrs.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplikasikrs.Admin.Adapter.DosenAdapter;
import com.example.aplikasikrs.Admin.Model.Dosen;
import com.example.aplikasikrs.Mahasiswa.Model.DefaultResult;
import com.example.aplikasikrs.Network.GetDataService;
import com.example.aplikasikrs.Network.RetrofitClientInstance;
import com.example.aplikasikrs.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateDosenActivity extends AppCompatActivity {

    private EditText nama_dosen;
    private EditText alamat_dosen;
    private EditText email_dosen;
    private EditText gelar_dosen;
    private EditText nidn_dosen;
    private EditText foto_dosen;
;
    ProgressDialog progressDialog;
    boolean isUpdate = false;
    String isDosen = "";
    private  String stringImg = "";
    GetDataService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dosen);
        this.setTitle("SI KRS - Hai Jonathan");
        checkUpdate();
        nama_dosen = (EditText) findViewById(R.id.editText);
        nidn_dosen = (EditText) findViewById(R.id.editText2);
        gelar_dosen = (EditText) findViewById(R.id.editText7);
        alamat_dosen = (EditText) findViewById(R.id.editText6);
        email_dosen = (EditText) findViewById(R.id.editText5);
        foto_dosen = (EditText) findViewById(R.id.txt_foto_dosen);

        progressDialog = new ProgressDialog(this);
        checkUpdate();
        Button edit_dosen = (Button)findViewById(R.id.btnSimpanDosen);
        if (isUpdate) {
            edit_dosen.setText("Update boss");
        }

    edit_dosen.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            if (!isUpdate){
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateDosenActivity.this);
                builder.setMessage("Mengubah Data?").setNegativeButton("No", new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog,int which)
                {Toast.makeText(CreateDosenActivity.this,"Tidak Menyimpan",Toast.LENGTH_SHORT).show(); }})
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {
                                update_dosen();
                            }});
                AlertDialog dialog = builder.create();dialog.show();

                            }
                        }
                });
        };


    void checkUpdate(){
        Bundle extras = getIntent().getExtras();
        if (extras == null){
            return;
        }
        isUpdate = extras.getBoolean("is_Update");
        isDosen = extras.getString("id_dosen");
        nama_dosen.setText(extras.getString("nama_dosen"));
        nidn_dosen.setText(extras.getString("nidn_dosen"));
        alamat_dosen.setText(extras.getString("alamat_dosen"));
        gelar_dosen.setText(extras.getString("gelar_dosen"));
        email_dosen.setText(extras.getString("email_dosen"));

    }

    private View.OnClickListener  dosen= new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder( CreateDosenActivity.this);
            builder.setMessage("Yakin ini menyimpan data ini ?")
                    .setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CreateDosenActivity.this,"gagal menyimpan",Toast.LENGTH_LONG).show();} })
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            insert_data();
                        }
                    });
            AlertDialog dialog = builder.create();dialog.show();
            }
        };
    private void update_dosen(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("masih loading sabar ...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DefaultResult> call = service.update_dosen(
                isDosen,
                nama_dosen.getText().toString(),
                alamat_dosen.getText().toString(),
                nidn_dosen.getText().toString(),
                gelar_dosen.getText().toString(),
                email_dosen.getText().toString(),
                foto_dosen.getText().toString(),
                "72170094");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();
                Toast.makeText(CreateDosenActivity.this, "Behasil Update !!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CreateDosenActivity.this,RecyclerViewDaftarDosen.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                progressDialog.
                        dismiss();
                Toast.makeText(CreateDosenActivity.this,"Gagal update",Toast.LENGTH_LONG);
            }
        });
    }



    private  void insert_data() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("iseh loading lek...");
        progressDialog.show();

        nama_dosen = (EditText) findViewById(R.id.editText);
        nidn_dosen = (EditText) findViewById(R.id.editText2);
        gelar_dosen = (EditText) findViewById(R.id.editText7);
        alamat_dosen = (EditText) findViewById(R.id.editText6);
        email_dosen = (EditText) findViewById(R.id.editText5);
        foto_dosen = (EditText) findViewById(R.id.txt_foto_dosen);

        GetDataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(GetDataService.class);
        Call<DefaultResult> call = service.insert_dosen(
                nama_dosen.getText().toString(),
                alamat_dosen.getText().toString(),
                nidn_dosen.getText().toString(),
                gelar_dosen.getText().toString(),
                email_dosen.getText().toString(),
                "https://picsum.photos/200/300/?blur=2",
                "72170094"
        );

        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();
                Toast.makeText(CreateDosenActivity.this, "berhasil save", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CreateDosenActivity.this, RecyclerViewDaftarDosen.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateDosenActivity.this, "Login gagal,coba lagi", Toast.LENGTH_LONG);

            }


        });
    }
}
