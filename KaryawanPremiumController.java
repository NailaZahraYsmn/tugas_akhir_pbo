import java.util.ArrayList;

public class KaryawanPremiumController {
    private ArrayList<KaryawanPremium> daftarPremium;

    public KaryawanPremiumController() {
        daftarPremium = new ArrayList<>();
    }

    public void tambah(KaryawanPremium k) {
        if (cari(k.getIdKaryawan()) != null) {
            System.out.println("ID sudah terdaftar: " + k.getIdKaryawan());
            return;
        }
        daftarPremium.add(k);
    }

    public ArrayList<KaryawanPremium> getDaftar() {
        return daftarPremium;
    }

    public void hapus(String idKaryawan) {
        KaryawanPremium k = cari(idKaryawan);
        if (k != null) {
            daftarPremium.remove(k);
        }
    }

    public KaryawanPremium cari(String idKaryawan) {
        for (KaryawanPremium k : daftarPremium) {
            if (k.getIdKaryawan().equalsIgnoreCase(idKaryawan)) {
                return k;
            }
        }
        return null;
    }

    public ArrayList<KaryawanPremium> filterByLevel(String level) {
        ArrayList<KaryawanPremium> hasil = new ArrayList<>();
        for (KaryawanPremium k : daftarPremium) {
            if (k.getLevelPremium().equalsIgnoreCase(level)) {
                hasil.add(k);
            }
        }
        return hasil;
    }

    public ArrayList<KaryawanPremium> sortByTunjangan() {
        ArrayList<KaryawanPremium> sorted = new ArrayList<>(daftarPremium);
        sorted.sort((a, b) -> Double.compare(b.getTunjangan(), a.getTunjangan()));
        return sorted;
    }
}

