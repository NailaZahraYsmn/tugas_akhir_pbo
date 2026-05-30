public class Jabatan implements Tampilan {
    private String kodeJabatan;
    private String namaJabatan;
    private String departemen;

    public Jabatan(String kodeJabatan, String namaJabatan, String departemen) {
        this.kodeJabatan = kodeJabatan;
        this.namaJabatan = namaJabatan;
        this.departemen = departemen;
    }

    public String getKodeJabatan() {
        return kodeJabatan;
    }
     public String getNamaJabatan() {
        return namaJabatan;
    }
    public String getDepartemen() {
        return departemen;
    }

    public void setNamaJabatan(String namaJabatan) {
        if (namaJabatan == null || namaJabatan.isEmpty()) {
            throw new IllegalArgumentException("Nama jabatan tidak boleh kosong.");
        }
        this.namaJabatan = namaJabatan;
    }
    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }
    @Override
    public String tampilkanInfo() {
        return String.format("[%s] %s - Departemen: %s", kodeJabatan, namaJabatan, departemen);
    }
}
