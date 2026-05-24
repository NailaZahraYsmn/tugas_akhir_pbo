public class KaryawanNonAktif extends Karyawan {
    private String tanggalKeluar;
    private final String status = "Non-Aktif";
    private String keterangan;

    public KaryawanNonAktif(String idKaryawan, String nama, Jabatan jabatan, String tanggalKeluar, String keterangan) {
        super(idKaryawan, nama, jabatan);
        if (tanggalKeluar == null || tanggalKeluar.isEmpty()) {
            throw new IllegalArgumentException("Tanggal keluar tidak boleh kosong.");
        }
        this.tanggalKeluar = tanggalKeluar;
        this.keterangan = keterangan;
    }

    public String getTanggalKeluar() {
        return tanggalKeluar;
    }

    public String getStatus() {
        return status;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setTanggalKeluar(String tanggalKeluar) {
        if (tanggalKeluar == null || tanggalKeluar.isEmpty()) {
            throw new IllegalArgumentException("Tanggal keluar tidak boleh kosong.");
        }
        this.tanggalKeluar = tanggalKeluar;
    }
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public String tampilkanInfo() {
        return String.format(
            "ID : %s\n" +
            "Nama : %s\n" +
            "Jabatan : %s\n" +
            "Departemen : %s\n" +
            "Tanggal Keluar : %s\n" +
            "Status : %s\n" +
            "Keterangan : %s\n",
            getIdKaryawan(),
            getNama(),
            getJabatan().getNamaJabatan(),
            getJabatan().getDepartemen(),
            getTanggalKeluar(),
            getStatus(),
            getKeterangan()
        );
    }
}
