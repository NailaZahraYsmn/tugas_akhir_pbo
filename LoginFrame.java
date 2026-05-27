import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    private AuthController authController;
    private KaryawanController karyawanController;
    private JabatanController jabatanController;
    private Perusahaan perusahaan;

    public LoginFrame(AuthController authController, KaryawanController karyawanController, JabatanController jabatanController, Perusahaan perusahaan) {
        this.authController     = authController;
        this.karyawanController = karyawanController;
        this.jabatanController  = jabatanController;
        this.perusahaan         = perusahaan;
        initUI();
    }

    private void initUI() {
        setTitle("Login - PT Maju Makmur");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(Color.WHITE);

        JLabel lblJudul = new JLabel("PT Maju Makmur", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblJudul.setForeground(new Color(25, 118, 210));
        panel.add(lblJudul);

        //username
        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(lblUser);

        txtUsername = new JTextField();
        panel.add(txtUsername);

        // password
        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(lblPass);

        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        btnLogin = new JButton("Masuk");
        btnLogin.setBackground(new Color(25, 118, 210));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(btnLogin);

        add(panel);
        setVisible(true);
    }
}