package entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guru {
    private int idGuru;
    private String nama;
    private String tempatLahir;
    private Date tglLahir;
    private String jenisKelamin;
    private String statusKepegawaian;
    private String mengajarMapel;
}

