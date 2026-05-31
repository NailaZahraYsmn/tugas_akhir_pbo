import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
 
public class KaryawanControllerTest {
 
    private KaryawanController karyawanCtrl;
    private Jabatan jabatan;
    private KaryawanAktif ka1;
    private KaryawanNonAktif kna1;
 
    @BeforeEach
    void setUp() {
        karyawanCtrl = new KaryawanController();
        jabatan      = new Jabatan("001", "Staff", "Tim Kreatif");
        ka1          = new KaryawanAktif("KAR001", "Naila Z", jabatan, "2022-01-15");
        kna1         = new KaryawanNonAktif("KAR010", "Dhea N", jabatan, "2024-06-30", "Pensiun");
    }
 
    @Test
    void testTambahAktifBerhasil() {
        karyawanCtrl.tambahAktif(ka1);
        assertNotNull(karyawanCtrl.cariAktif("KAR001"));
    }
 
    @Test
    void testTambahAktifDuplikatDitolak() {
        karyawanCtrl.tambahAktif(ka1);
        karyawanCtrl.tambahAktif(ka1);
        assertEquals(1, karyawanCtrl.getDaftarAktif().size());
    }
 
    @Test
    void testTambahNonAktifBerhasil() {
        karyawanCtrl.tambahNonAktif(kna1);
        assertNotNull(karyawanCtrl.cariNonAktif("KAR010"));
    }
 
    @Test
    void testEditAktifBerhasil() {
        karyawanCtrl.tambahAktif(ka1);
        KaryawanAktif dataBaru = new KaryawanAktif("KAR001", "Naila", jabatan, "2022-01-15");
        karyawanCtrl.editAktif("KAR001", dataBaru);
        assertEquals("Naila", karyawanCtrl.cariAktif("KAR001").getNama());
    }
 
    @Test
    void testHapusAktifBerhasil() {
        karyawanCtrl.tambahAktif(ka1);
        karyawanCtrl.hapusAktif("KAR001");
        assertNull(karyawanCtrl.cariAktif("KAR001"));
    }
 
    @Test
    void testHapusNonAktifBerhasil() {
        karyawanCtrl.tambahNonAktif(kna1);
        karyawanCtrl.hapusNonAktif("KAR010");
        assertNull(karyawanCtrl.cariNonAktif("KAR010"));
    }
 
    @Test
    void testNonAktifkanKaryawanBerhasil() {
        karyawanCtrl.tambahAktif(ka1);
        karyawanCtrl.nonAktifkanKaryawan("KAR001", "2025-12-31", "Resign");
        assertNull(karyawanCtrl.cariAktif("KAR001"));
        assertNotNull(karyawanCtrl.cariNonAktif("KAR001"));
    }
}