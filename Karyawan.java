public abstract class Karyawan implements Tampilan {
    private String idKaryawan;
    private String nama;
    private Jabatan jabatan;

    public Karyawan(String idKaryawan, String nama, Jabatan jabatan) {
        if (idKaryawan == null || idKaryawan.isEmpty()) {
            throw new IllegalArgumentException("ID Karyawan tidak boleh kosong.");
        }
        if (nama == null || nama.isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong.");
        }
        if(jabatan == null) {
            throw new IllegalArgumentException("Jabatan tidak boleh null.");
        }
        this.idKaryawan = idKaryawan;
        this.nama = nama;
        this.jabatan = jabatan;
    }

    public String getIdKaryawan() {
        return idKaryawan;
    }
    public String getNama() {
        return nama;
    }
    public Jabatan getJabatan() {
        return jabatan;
    }
    public void setNama(String nama) {
        if (nama == null || nama.isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong.");
        }
        this.nama = nama;
    }
    public void setJabatan(Jabatan jabatan) {
        if(jabatan == null) {
            throw new IllegalArgumentException("Jabatan tidak boleh null.");
        }
        this.jabatan = jabatan;
    }
    @Override
    public String tampilkanInfo() {
        return String.format("ID: %s\nNama: %s\nJabatan: %s", idKaryawan, nama, jabatan.tampilkanInfo());
    }

}
