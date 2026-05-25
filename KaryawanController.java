import java.util.ArrayList;

public class KaryawanController {

    private ArrayList<KaryawanAktif> daftarAktif;
    private ArrayList<KaryawanNonAktif> daftarNonAktif;

    public KaryawanController() {
        daftarAktif = new ArrayList<>();
        daftarNonAktif = new ArrayList<>();
    }

    public void tambahAktif(KaryawanAktif karyawan) {
        daftarAktif.add(karyawan);
    }

    public void tambahNonAktif(KaryawanNonAktif karyawan) {
        daftarNonAktif.add(karyawan);
    }

    public ArrayList<KaryawanAktif> getDaftarAktif() {
        return daftarAktif;
    }

    public ArrayList<KaryawanNonAktif> getDaftarNonAktif() {
        return daftarNonAktif;
    }
}