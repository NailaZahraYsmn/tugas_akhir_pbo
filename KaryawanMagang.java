public class KaryawanMagang extends KaryawanAktif {

    private String namaUniversitas;
    private String jurusan;
    private int durasiMagang;

    public KaryawanMagang(String idKaryawan, String nama, Jabatan jabatan,
                           String tanggalMasuk, String namaUniversitas,
                           String jurusan, int durasiMagang)
                           throws InputTidakValidException {

        super(idKaryawan, nama, jabatan, tanggalMasuk);
        setNamaUniversitas(namaUniversitas);
        setJurusan(jurusan);
        setDurasiMagang(durasiMagang);
    }


    public String getNamaUniversitas() { return namaUniversitas; }
    public String getJurusan()         { return jurusan; }
    public int    getDurasiMagang()    { return durasiMagang; }


    public void setNamaUniversitas(String namaUniversitas) throws InputTidakValidException {
        if (namaUniversitas == null || namaUniversitas.isEmpty())
            throw new InputTidakValidException("Peringatan! Nama universitas tidak boleh kosong.");
        this.namaUniversitas = namaUniversitas;
    }

    public void setJurusan(String jurusan) throws InputTidakValidException {
        if (jurusan == null || jurusan.isEmpty())
            throw new InputTidakValidException("Peringatan! Jurusan tidak boleh kosong.");
        this.jurusan = jurusan;
    }

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
            "Universitas : %s\n" +
            "Jurusan : %s\n" +
            "Durasi Magang : %d bulan\n" +
            "[KARYAWAN MAGANG]\n",
            getIdKaryawan(),
            getNama(),
            getJabatan().getNamaJabatan(),
            getJabatan().getDepartemen(),
            getTanggalMasuk(),
            getStatus(),
            namaUniversitas,
            jurusan,
            durasiMagang
        );
    }
}