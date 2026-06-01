import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
 
public class JabatanControllerTest {
 
    private JabatanController jabatanCtrl;
    private Jabatan jabatan1;
 
    @BeforeEach
    void setUp() {
        jabatanCtrl = new JabatanController();
        jabatan1 = new Jabatan("J001", "Staff", "Tim IT");
    }


    @Test
    void testTambahJabatanBerhasil() {
        jabatanCtrl.tambah(jabatan1);
        assertNotNull(jabatanCtrl.cari("J001"));
    }
 
    @Test
    void testTambahJabatanDuplikatDitolak() {
        jabatanCtrl.tambah(jabatan1);
        jabatanCtrl.tambah(jabatan1);
        assertEquals(1, jabatanCtrl.getDaftarJabatan().size());
    }
 
    @Test
    void testCariJabatanDitemukan() {
        jabatanCtrl.tambah(jabatan1);
        assertEquals("Staff", jabatanCtrl.cari("J001").getNamaJabatan());
    }
 
    @Test
    void testCariJabatanTidakDitemukan() {
        assertNull(jabatanCtrl.cari("J009"));
    }
 
    @Test
    void testEditJabatanBerhasil() {
        jabatanCtrl.tambah(jabatan1);
        Jabatan dataBaru = new Jabatan("J001", "Staff Senior", "Teknologi & Inovasi");
        jabatanCtrl.edit("J001", dataBaru);
        assertEquals("Staff Senior", jabatanCtrl.cari("J001").getNamaJabatan());
    }
 
    @Test
    void testHapusJabatanBerhasil() {
        jabatanCtrl.tambah(jabatan1);
        jabatanCtrl.hapus("J001");
        assertNull(jabatanCtrl.cari("J001"));
    }
 
    @Test
    void testEditJabatanNullDitolak() {
        jabatanCtrl.tambah(jabatan1);
        jabatanCtrl.edit("J001", null);
        assertEquals("Staff", jabatanCtrl.cari("J001").getNamaJabatan());
    }
}