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
        setTitle("Login - PT Makmur Jaya");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(Color.WHITE);

        JLabel lblJudul = new JLabel("PT Makmur Jaya", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblJudul.setForeground(new Color(25, 118, 210));
        panel.add(lblJudul);

        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(lblUser);

        txtUsername = new JTextField();
        panel.add(txtUsername);

        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.addActionListener(e -> prosesLogin());
        panel.add(txtPassword);

        btnLogin = new JButton("Masuk");
        btnLogin.setBackground(new Color(25, 118, 210));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLogin.addActionListener(e -> prosesLogin());
        
        panel.add(btnLogin);

        add(panel);
        setVisible(true);
    }

    private void prosesLogin() {
        try {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            if (username.isEmpty())
                throw new IllegalArgumentException("Username tidak boleh kosong!");
            if (password.isEmpty())
                throw new IllegalArgumentException("Password tidak boleh kosong!");
            if (username.length() < 3)
                throw new IllegalArgumentException("Username minimal 3 karakter!");
            if (password.length() < 6)
                throw new IllegalArgumentException("Password minimal 6 karakter!");

            boolean berhasil = authController.login(username, password);
            if (berhasil) {
                JOptionPane.showMessageDialog(this, "Login berhasil! Selamat datang, " + username, "Sukses", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new MainFrame(authController, karyawanController, jabatanController, perusahaan);
            } else {
                JOptionPane.showMessageDialog(this, "Username atau password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                txtPassword.setText("");
                txtPassword.requestFocus();
            }

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validasi Error", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan sistem!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }   
}