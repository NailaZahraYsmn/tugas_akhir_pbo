public class KaryawanMagang extends Karyawan {
    private String DurasiMagang; 
    public KaryawanMagang (String idKaryawan, String nama, Jabatan jabatan, String DurasiMagang) {
        super(idKaryawan, nama, jabatan);
        if (DurasiMagang == null || DurasiMagang.isEmpty()) {
            throw new IllegalArgumentException("Durasi magang tidak boleh kosong.");
        }
        this.DurasiMagang = DurasiMagang;
    }
}
