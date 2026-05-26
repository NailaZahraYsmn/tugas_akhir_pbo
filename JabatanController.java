import java.util.ArrayList;

public class JabatanController {

    private ArrayList<Jabatan> daftarJabatan;
    private ArrayList<KaryawanNonAktif> daftarNonAktif; 

    public JabatanController() {
        daftarJabatan  = new ArrayList<>();
        daftarNonAktif = new ArrayList<>(); 
    }

    public ArrayList<KaryawanNonAktif> getDaftarNonAktif() {
        return daftarNonAktif;
    }

    public ArrayList<Jabatan> getDaftarJabatan() {
        return daftarJabatan;
    }

    public void tambah(Jabatan jabatan) {
        if (cari(jabatan.getKodeJabatan()) != null) {
            System.out.println("Kode jabatan sudah ada: " + jabatan.getKodeJabatan());
            return;
        }
        daftarJabatan.add(jabatan);
    }


    public Jabatan cari(String kodeJabatan) {

        for (Jabatan jabatan : daftarJabatan) {

            if (jabatan.getKodeJabatan().equalsIgnoreCase(kodeJabatan)) {
                return jabatan;
            }
        }

        return null;
    }

    public void edit(String kodeJabatan, Jabatan dataBaru) {
        if (dataBaru == null) {
            System.out.println("Data baru tidak boleh Kosong.");
            return;
        }
        Jabatan jabatan = cari(kodeJabatan);

        if (jabatan != null) {

            jabatan.setNamaJabatan(dataBaru.getNamaJabatan());
            jabatan.setDepartemen(dataBaru.getDepartemen());
        } else {
            System.out.println("Jabatan tidak ditemukan: " + kodeJabatan);
        }
    }

    public void hapus(String kodeJabatan) {
        Jabatan jabatan = cari(kodeJabatan);

        if (jabatan != null) {
            daftarJabatan.remove(jabatan);
            System.out.println("Jabatan berhasil dihapus: " + kodeJabatan);
        } else {
            System.out.println("Jabatan tidak ditemukan: " + kodeJabatan);
        }
    }

    public void tampilkanSemua() {
        if (daftarJabatan.isEmpty()) {
            System.out.println("Belum ada data jabatan.");
            return;
        }
        for (Jabatan j : daftarJabatan) {
            System.out.println(j.tampilkanInfo());
        }
    }
}