public class Perusahaan implements Tampilan{
    private String nama;
    private String alamat;
    private String bidangUsaha;
    private  final int tahunBerdiri;
    private int jumlahKaryawan;
    private String visi;

    public Perusahaan(String nama, String alamat, String bidangUsaha, int tahunBerdiri, int jumlahKaryawan, String visi) {
        this.nama = nama;
        this.alamat = alamat;
        this.bidangUsaha = bidangUsaha;
        this.tahunBerdiri = tahunBerdiri;
        this.jumlahKaryawan = jumlahKaryawan;
        this.visi = visi;
    }

     public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getBidangUsaha() {
        return bidangUsaha;
    }

    public int getTahunBerdiri() {
        return tahunBerdiri;
    }

    public int getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public String getVisi() {
        return visi;
    }
    public void setNama(String nama) {
        if (nama == null || nama.isEmpty()) {
            throw new IllegalArgumentException("Nama perusahaan tidak boleh kosong.");
        }

        this.nama = nama;
    }
    public void setAlamat(String alamat) {
        if (alamat == null || alamat.isEmpty()) {
            throw new IllegalArgumentException("Alamat perusahaan tidak boleh kosong.");
        }

        this.alamat = alamat;
    }

    public void setBidangUsaha(String bidangUsaha) {
        this.bidangUsaha = bidangUsaha;
    }
    
    public void setJumlahKaryawan(int jumlahKaryawan) {
        if (jumlahKaryawan < 0) {
            throw new IllegalArgumentException("Jumlah karyawan tidak boleh negatif.");
        }

        this.jumlahKaryawan = jumlahKaryawan;
    }
    public void setVisi(String visi) {
        this.visi = visi;
    }

    @Override
    public String tampilkanInfo() {
        return String.format("Perusahaan: %s\n"+ 
                            "Alamat: %s\n"+
                            "Bidang Usaha: %s\n"+
                            "Tahun Berdiri: %d\n"+
                            "Jumlah Karyawan: %d\n"+
                            "Visi: %s",
                nama, alamat, bidangUsaha, tahunBerdiri, jumlahKaryawan, visi);}
        
}
