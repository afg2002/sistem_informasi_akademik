package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransaksiPembayaran {
    private int noTransaksi;
    private int nis;
    private Date tglPembayaran;
    private String kodePembayaran;
    private double jumlahBayar;
}
