public class KaryawanMagang extends KaryawanAktif {

    private int durasiMagang;

    public KaryawanMagang(String idKaryawan, String nama, Jabatan jabatan,
                           String tanggalMasuk,  int durasiMagang)
                           throws InputTidakValidException {

        super(idKaryawan, nama, jabatan, tanggalMasuk);
        setDurasiMagang(durasiMagang);
    }

    public int    getDurasiMagang()    { return durasiMagang; }


    public void setDurasiMagang(int durasiMagang) throws InputTidakValidException {
        if (durasiMagang <= 0)
            throw new InputTidakValidException("Peringatan! Durasi magang harus lebih dari 0 bulan.");
        this.durasiMagang = durasiMagang;
    }

    @Override
    public String tampilkanInfo() {
        return String.format(
            "ID : %s\n" +
            "Nama : %s\n" +
            "Jabatan : %s\n" +
            "Departemen : %s\n" +
            "Tanggal Masuk : %s\n" +
            "Status : %s\n" +
            "Durasi Magang : %d bulan\n" +
            "[KARYAWAN MAGANG]\n",
            getIdKaryawan(),
            getNama(),
            getJabatan().getNamaJabatan(),
            getJabatan().getDepartemen(),
            getTanggalMasuk(),
            getStatus(),
            durasiMagang
        );
    }
}