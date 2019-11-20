package com.example.aplikasikrs.Admin.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Dosen {
    @SerializedName("nidn")
    @Expose
    private String nidn;
    @SerializedName("namaDosen")
    @Expose
    private String namaDosen;
    @SerializedName("gelar")
    @Expose
    private String gelar;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("Id")
    @Expose
    private String Id;

    public Dosen(String nidn, String namaDosen, String gelar, String email, String alamat, String foto) {
        this.nidn = nidn;
        this.namaDosen = namaDosen;
        this.gelar = gelar;
        this.email = email;
        this.alamat = alamat;
        this.foto = foto;
    }
    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
