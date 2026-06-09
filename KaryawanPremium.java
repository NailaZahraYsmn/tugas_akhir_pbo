public class KaryawanPremium extends KaryawanAktif {
    private double tunjangan;
    private String levelPremium;

    public KaryawanPremium(String idKaryawan, String nama, Jabatan jabatan, String tanggalMasuk, double tunjangan, String levelPremium) {
        super(idKaryawan, nama, jabatan, tanggalMasuk);
        if (tunjangan < 0)
            throw new IllegalArgumentException("Tunjangan tidak boleh negatif!");
        if (levelPremium == null || levelPremium.isEmpty())
            throw new IllegalArgumentException("Level premium tidak boleh kosong!");
        this.tunjangan    = tunjangan;
        this.levelPremium = levelPremium;
    }

    public double getTunjangan() {
        return tunjangan; 
    }
    public String getLevelPremium() {
        return levelPremium; 
    }

    public void setTunjangan(double tunjangan) {
        if (tunjangan < 0)
            throw new IllegalArgumentException("Tunjangan tidak boleh negatif!");
        this.tunjangan = tunjangan;
    }
    public void setLevelPremium(String levelPremium) {
        if (levelPremium == null || levelPremium.isEmpty())
            throw new IllegalArgumentException("Level premium tidak boleh kosong!");
        this.levelPremium = levelPremium;
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
            "Level Premium : %s\n" +
            "Tunjangan : Rp %.0f\n",
            getIdKaryawan(),
            getNama(),
            getJabatan().getNamaJabatan(),
            getJabatan().getDepartemen(),
            getTanggalMasuk(),
            getStatus(),
            levelPremium,
            tunjangan
        );
    }
}