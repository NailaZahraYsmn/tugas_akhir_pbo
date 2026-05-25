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
}