import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        AuthController authController         = new AuthController();
        KaryawanController karyawanController = new KaryawanController();
        JabatanController jabatanController   = new JabatanController();

        Perusahaan perusahaan = new Perusahaan(
            "PT Makmur Jaya",
            "Madiun",
            "Furniture",
            2010,
            50,
            "Hadirkan rumah impian dengan furniture terbaik"
        );

        Jabatan jab1 = new Jabatan("JB05", "UI/UX Designer", "Teknologi");
        Jabatan jab2 = new Jabatan("JB06", "Backend Developer", "Teknologi");

        // Membuat objek dari subclass KaryawanMagang
        KaryawanMagang magang1 = new KaryawanMagang("M001", "Naila Zahra", jab1, "3 Bulan");
        KaryawanMagang magang2 = new KaryawanMagang("M002", "sutyo aji", jab2, "6 Bulan");
        KaryawanMagang magang3 = new KaryawanMagang("M003", "firmansyah", jab1, "4 Bulan");

        // Memasukkan ke dalam controller
        karyawanController.tambahMagang(magang1);
        karyawanController.tambahMagang(magang2);
        karyawanController.tambahMagang(magang3);

        SwingUtilities.invokeLater(() -> {
            new LoginFrame(authController, karyawanController, jabatanController, perusahaan);
        });
    }
}
