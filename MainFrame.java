import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private AuthController authController;
    private KaryawanController karyawanController;
    private JabatanController jabatanController;
    private Perusahaan perusahaan;

    private static final Color BIRU = new Color(25, 118, 210);
    private static final Color MERAH = new Color(198, 40, 40);

    public MainFrame(AuthController authController, KaryawanController karyawanController, JabatanController jabatanController, Perusahaan perusahaan) {
        this.authController     = authController;
        this.karyawanController = karyawanController;
        this.jabatanController  = jabatanController;
        this.perusahaan         = perusahaan;
        initUI();
    }

    private void initUI() {
        setTitle("PT Makmur Jaya");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // header
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BIRU);
        topPanel.setPreferredSize(new Dimension(0, 45));

        JLabel lblJudul = new JLabel("PT Makmur Jaya - Informasi Karyawan");
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblJudul.setForeground(Color.WHITE);
        topPanel.add(lblJudul, BorderLayout.WEST);

        // logout
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBackground(MERAH);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorderPainted(false);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLogout.addActionListener(e -> {
            int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Yakin ingin logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                authController.logout();
                dispose();
                new LoginFrame(authController, karyawanController, jabatanController, perusahaan);
            }
        });
        topPanel.add(btnLogout, BorderLayout.EAST);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabs.addTab("Karyawan Aktif", new PanelKaryawanAktif(karyawanController));
        tabs.addTab("Karyawan Non-Aktif", new PanelKaryawanNonAktif(karyawanController));
        tabs.addTab("Perusahaan", new PanelPerusahaan(perusahaan));

        add(topPanel, BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);
        setVisible(true);
    }
}