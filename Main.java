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

        SwingUtilities.invokeLater(() -> {
            new LoginFrame(authController, karyawanController, jabatanController, perusahaan);
        });
    }
}
