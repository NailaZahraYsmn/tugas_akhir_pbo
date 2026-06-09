public class KaryawanMagang extends Karyawan {
    private String DurasiMagang; 
    public KaryawanMagang (String idKaryawan, String nama, Jabatan jabatan, String DurasiMagang) {
        super(idKaryawan, nama, jabatan);
        if (DurasiMagang == null || DurasiMagang.isEmpty()) {
            throw new IllegalArgumentException("Durasi magang tidak boleh kosong.");
        }
        this.DurasiMagang = DurasiMagang;
    }
    public String getDurasiMagang() {
        return DurasiMagang;
    }
    public void setDurasiMagang(String DurasiMagang) {
        if (DurasiMagang == null || DurasiMagang.isEmpty()) {
            throw new IllegalArgumentException("Durasi magang tidak boleh kosong.");
        }
        this.DurasiMagang = DurasiMagang;
    }
    @Override
    public String tampilkanInfo() {
        return String.format(
            "ID : %s\n" +
            "Nama : %s\n" +
            "Jabatan : %s\n" +
            "Departemen : %s\n" +
            "Durasi Magang : %s\n",
            getIdKaryawan(),
            getNama(),
            getJabatan().getNamaJabatan(),
            getJabatan().getDepartemen(),
            getDurasiMagang()
        );
    }
}
