import java.util.ArrayList;

public class JabatanController {

    private ArrayList<Jabatan> daftarJabatan;

    public JabatanController() {
        daftarJabatan = new ArrayList<>();
    }

    public void tambah(Jabatan jabatan) {
        daftarJabatan.add(jabatan);
    }

    public ArrayList<Jabatan> getDaftarJabatan() {
        return daftarJabatan;
    }

    public Jabatan cari(String kodeJabatan) {

        for (Jabatan jabatan : daftarJabatan) {

            if (jabatan.getKodeJabatan().equalsIgnoreCase(kodeJabatan)) {
                return jabatan;
            }
        }

        return null;
    }
}