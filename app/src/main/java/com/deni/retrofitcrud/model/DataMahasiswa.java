package com.deni.retrofitcrud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Deni Supriyatna on 10 - Mar - 2020.
 * Email : denisupriyatna01@gmail.com
 */
public class DataMahasiswa {
    @SerializedName("npm")
    @Expose
    private String npm;
    @SerializedName("nama_mahasiswa")
    @Expose
    private String namaMahasiswa;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("jurusan")
    @Expose
    private String jurusan;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("agama")
    @Expose
    private String agama;

    public DataMahasiswa(){}

    public DataMahasiswa(String npm, String namaMahasiswa, String jenisKelamin, String jurusan, String noHp, String email, String agama){
        this.npm = npm;
        this.namaMahasiswa = namaMahasiswa;
        this.jenisKelamin = jenisKelamin;
        this.jurusan = jurusan;
        this.noHp = noHp;
        this.email = email;
        this.agama = agama;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }
}
