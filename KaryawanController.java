import java.util.ArrayList;

public class KaryawanController {

    private ArrayList<KaryawanAktif> daftarAktif;
    private ArrayList<KaryawanNonAktif> daftarNonAktif;

    public KaryawanController() {
        daftarAktif = new ArrayList<>();
        daftarNonAktif = new ArrayList<>();
    }

    public void tambahAktif(KaryawanAktif karyawan) {
        if (cariAktif(karyawan.getIdKaryawan()) != null) {
            System.out.println("ID sudah terdaftar : " + karyawan.getIdKaryawan());
            return;
        }
        daftarAktif.add(karyawan);
    }

    public void tambahNonAktif(KaryawanNonAktif karyawan) {
        if (cariNonAktif(karyawan.getIdKaryawan()) != null) {
            System.out.println("ID sudah terdaftar: " + karyawan.getIdKaryawan());
            return;
        }
        daftarNonAktif.add(karyawan);
    }

    public ArrayList<KaryawanAktif> getDaftarAktif() {
        return daftarAktif;
    }

    public ArrayList<KaryawanNonAktif> getDaftarNonAktif() {
        return daftarNonAktif;
    }

    public KaryawanAktif cariAktif(String idKaryawan) {

        for (KaryawanAktif karyawan : daftarAktif) {

            if (karyawan.getIdKaryawan().equalsIgnoreCase(idKaryawan)) {
                return karyawan;
            }
        }

        return null;
    }

    public KaryawanNonAktif cariNonAktif(String idKaryawan) {

        for (KaryawanNonAktif karyawan : daftarNonAktif) {

            if (karyawan.getIdKaryawan().equalsIgnoreCase(idKaryawan)) {
                return karyawan;
            }
        }

        return null;
    }

    public void editAktif(String idKaryawan, KaryawanAktif dataBaru) {
        if (dataBaru == null) {
            System.out.println("Data baru tidak boleh Kosong.");
            return;
        }

        KaryawanAktif karyawan = cariAktif(idKaryawan);

        if (karyawan != null) {
            
            karyawan.setNama(dataBaru.getNama());
            karyawan.setJabatan(dataBaru.getJabatan());
            karyawan.setTanggalMasuk(dataBaru.getTanggalMasuk());
        } else {
            System.out.println("Karyawan tidak ditemukan: " + idKaryawan);
        }
    }

    public void editNonAktif(String idKaryawan, KaryawanNonAktif dataBaru) {
        if (dataBaru == null) {
            System.out.println("Data baru tidak boleh Kosong.");
            return;
        }
        KaryawanNonAktif karyawan = cariNonAktif(idKaryawan);

        if (karyawan != null) {

            karyawan.setNama(dataBaru.getNama());
            karyawan.setJabatan(dataBaru.getJabatan());
            karyawan.setTanggalKeluar(dataBaru.getTanggalKeluar());
            karyawan.setKeterangan(dataBaru.getKeterangan());
        } else {
            System.out.println("Karyawan tidak ditemukan: " + idKaryawan);
        }
    }

    public void hapusAktif(String idKaryawan) {

        KaryawanAktif karyawan = cariAktif(idKaryawan);

        if (karyawan != null) {
            daftarAktif.remove(karyawan);
        }
    }

    public void hapusNonAktif(String idKaryawan) {

        KaryawanNonAktif karyawan = cariNonAktif(idKaryawan);

        if (karyawan != null) {
            daftarNonAktif.remove(karyawan);
        }
    }

    public void tampilkanSemuaAktif() {
        if (daftarAktif.isEmpty()) {
            System.out.println("Tidak ada karyawan aktif.");
            return;
        }
        for (KaryawanAktif k : daftarAktif) {
            System.out.println(k.tampilkanInfo());
        }
    }

    public void tampilkanSemuaNonAktif() {
        if (daftarNonAktif.isEmpty()) {
            System.out.println("Tidak ada karyawan non-aktif.");
            return;
        }
        for (KaryawanNonAktif k : daftarNonAktif) {
            System.out.println(k.tampilkanInfo());
        }
    }

    public void nonAktifkanKaryawan(String idKaryawan, String tanggalKeluar, String keterangan) {
        KaryawanAktif aktif = cariAktif(idKaryawan);
        if (aktif == null) {
            System.out.println("Karyawan tidak ditemukan: " + idKaryawan);
            return;
        }

        KaryawanNonAktif nonAktif = new KaryawanNonAktif(
            aktif.getIdKaryawan(),
            aktif.getNama(),         
            aktif.getJabatan(),      
            tanggalKeluar,           
            keterangan               
        );

        daftarNonAktif.add(nonAktif);
        daftarAktif.remove(aktif);
        System.out.println("Karyawan " + aktif.getNama() + " dipindahkan ke NonAktif.");
    }
}