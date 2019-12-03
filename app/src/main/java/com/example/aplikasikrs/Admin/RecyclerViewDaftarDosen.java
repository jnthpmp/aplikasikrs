package com.example.aplikasikrs.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aplikasikrs.Admin.Adapter.DosenAdapter;
import com.example.aplikasikrs.Admin.Model.Dosen;
import com.example.aplikasikrs.Mahasiswa.Model.DefaultResult;
import com.example.aplikasikrs.MainActivity;
import com.example.aplikasikrs.Network.GetDataService;
import com.example.aplikasikrs.Network.RetrofitClientInstance;
import com.example.aplikasikrs.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;




public class RecyclerViewDaftarDosen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DosenAdapter dosenAdapter;
    private ArrayList<Dosen> dosenList;
    ProgressDialog progressDialog;

    boolean isUpdate;
    String isDosen;





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucreate,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu1){
            Intent intent = new Intent(RecyclerViewDaftarDosen.this,CreateDosenActivity.class);
            startActivity(intent);
        }
        return  true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_daftar_dosen);
        this.setTitle("SI KRS - Hai Admin");
        recyclerView = (RecyclerView) findViewById(R.id.rvDosen);


        /* tambahData();
       */
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tunggu sekk.....");
        progressDialog.show();



        GetDataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(GetDataService.class);
        Call<ArrayList<Dosen>> call = service.getDosenAll("72170094");
        call.enqueue(new Callback<ArrayList<Dosen>>() {
            @Override
            public void onResponse(Call<ArrayList<Dosen>> call, Response<ArrayList<Dosen>> response) {
                progressDialog.dismiss();
                recyclerView = findViewById(R.id.rvDosen);
                dosenAdapter = new DosenAdapter(response.body());
                dosenList = response.body();
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewDaftarDosen.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(dosenAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Dosen>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RecyclerViewDaftarDosen.this,"Login gagal,coba lagi",Toast.LENGTH_LONG);

            }





        });
    registerForContextMenu(recyclerView);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Dosen dosen = dosenList.get(item.getGroupId());
        if (item.getTitle()=="Ubah Data Dosen"){
            Intent intent = new Intent(RecyclerViewDaftarDosen.this,CreateDosenActivity.class);
            intent.putExtra("id_dosen",dosen.getId());
            intent.putExtra("nama_dosen",dosen.getNamaDosen());
            intent.putExtra("nidn_dosen",dosen.getNidn());
            intent.putExtra("alamat_dosen",dosen.getAlamat());
            intent.putExtra("email_dosen",dosen.getEmail());
            intent.putExtra("gelar_dosen",dosen.getGelar());
            intent.putExtra("foto_dosen",dosen.getFoto());
            intent.putExtra("is_update",true);
            startActivity(intent);
                    }
        else if(item.getTitle()== "hapus data dosen") {
            progressDialog = new ProgressDialog(RecyclerViewDaftarDosen.this);
            progressDialog.show();
        }

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DefaultResult> call = service.delete_dosen(
                dosen.getId(),
                "72170094"
        );
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();
                Toast.makeText(RecyclerViewDaftarDosen.this,"Berhasil Hapus Dosen",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RecyclerViewDaftarDosen.this,RecyclerViewDaftarDosen.class);
                startActivity(intent);
                finish();

            }
            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RecyclerViewDaftarDosen.this,"gagal menghapus",Toast.LENGTH_LONG).show();
            }
        });

        return super.onContextItemSelected(item);

    }


    /*private void tambahData(){
        *//*dosenList = new ArrayList<>();
        dosenList.add(new Dosen("001","Argo Wibowo", "Proffesor","argo@staff.ukdw.ac.id","Jl. Magelang",R.drawable.logo));
        dosenList.add(new Dosen("001","Argo Wibowo", "Proffesor","argo@staff.ukdw.ac.id","Jl. Magelang",R.drawable.logo));
        dosenList.add(new Dosen("001","Argo Wibowo", "Proffesor","argo@staff.ukdw.ac.id","Jl. Magelang",R.drawable.logo));*//*
    }*/
}
