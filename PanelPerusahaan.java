import javax.swing.*;
import java.awt.*;

public class PanelPerusahaan extends JPanel {

    private Perusahaan perusahaan;
    private static final Color BIRU = new Color(25, 118, 210);

    public PanelPerusahaan(Perusahaan perusahaan) {
        this.perusahaan = perusahaan;
        initUI();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1.0;
        g.insets = new Insets(8, 0, 8, 0);

        JLabel lblJudul = new JLabel("Informasi Perusahaan", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblJudul.setForeground(BIRU);
        g.gridy = 0;
        panel.add(lblJudul, g);

        JSeparator sep = new JSeparator();
        g.gridy = 1;
        add(sep, g);

        String[] info = perusahaan.tampilkanInfo().split("\n");
        for (int i = 0; i < info.length; i++) {
            JLabel lbl = new JLabel(info[i]);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lbl.setForeground(new Color(50, 50, 50));
            g.gridy = i + 2;
            add(lbl, g);
        }
    }
}