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
        setBackground(new Color(236, 239, 241));

        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 210, 220), 1),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1.0;
        g.insets = new Insets(6, 0, 6, 0);

        JLabel lblJudul = new JLabel("Informasi Perusahaan", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblJudul.setForeground(BIRU);
        g.gridy = 0;
        g.insets = new Insets(0, 0, 10, 0);
        card.add(lblJudul, g);

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(200, 210, 220));
        g.gridy = 1;
        g.insets = new Insets(0, 0, 16, 0);
        card.add(sep, g);

        String[] info = perusahaan.tampilkanInfo().split("\n");
        g.insets = new Insets(6, 0, 6, 0);
        for (int i = 0; i < info.length; i++) {
            JLabel lbl = new JLabel(info[i]);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            lbl.setForeground(new Color(50, 50, 70));
            g.gridy = i + 2;
            card.add(lbl, g);
        }

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.ipadx = 200;
        add(card, gc);
    }
}