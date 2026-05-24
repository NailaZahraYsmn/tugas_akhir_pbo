public class KaryawanAktif extends Karyawan {
    private String tanggalMasuk;
    private final String status = "Aktif";

    public KaryawanAktif(String idKaryawan, String nama, Jabatan jabatan, String tanggalMasuk){
        super(idKaryawan, nama, jabatan);
        if (tanggalMasuk == null || tanggalMasuk.isEmpty()) {
            throw new IllegalArgumentException("Tanggal masuk tidak boleh kosong.");
        }
        this.tanggalMasuk = tanggalMasuk;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }
    public String getStatus() {
        return status;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        if (tanggalMasuk == null || tanggalMasuk.isEmpty()) {
            throw new IllegalArgumentException("Tanggal masuk tidak boleh kosong.");
        }
        this.tanggalMasuk = tanggalMasuk;
    }

    @Override
    public String tampilkanInfo() {
        return String.format(
            "ID : %s\n" +
            "Nama : %s\n" +
            "Jabatan : %s\n" +
            "Departemen : %s\n" +
            "Tanggal Masuk : %s\n" +
            "Status : %s\n",
            getIdKaryawan(),
            getNama(),
            getJabatan().getNamaJabatan(),
            getJabatan().getDepartemen(),
            getTanggalMasuk(),
            getStatus()
        );
    }

}
