/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Siswa {
    private String nis;
    private String nama;
    private String tempatLahir;
    private Date tglLahir;
    private String jenisKelamin;
    private String nisn;
    private int angkatan;
    private String kelas;
    private String agama;
    private String alamat;
    private String noTelp;
    private String namaAyah;
    private Date tglLahirAyah;
    private String pendidikanAyah;
    private String pekerjaanAyah;
    private String namaIbu;
    private Date tglLahirIbu;
    private String pendidikanIbu;
    private String pekerjaanIbu;
}
