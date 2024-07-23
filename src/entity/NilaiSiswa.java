package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NilaiSiswa {
    private int idNilai;
    private String tahunPelajaran;
    private int semester;
    private String kelas;
    private int angkatan;
    private String nis;
    private String kodeMapel;
    private int idGuru;
    private double nilaiAbsen;
    private double nilaiHarian;
    private double nilaiUts;
    private double nilaiUas;
    private double nilaiRata2;  // This is auto-generated in the database, so it can be calculated in the application as needed
    private String grade;
    private String keterangan;
}
