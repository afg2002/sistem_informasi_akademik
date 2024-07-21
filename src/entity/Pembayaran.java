package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pembayaran {
    private String kodePembayaran;
    private JenisPembayaran jenisPembayaran;
    private double nominal;
}
