import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelKaryawanAktif extends JPanel {
    private KaryawanController karyawanController;
    private JTable tabel;
    private DefaultTableModel modelTabel;
    private static final Color BIRU = new Color(25, 118, 210);

    public PanelKaryawanAktif(KaryawanController karyawanController) {
        this.karyawanController = karyawanController;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        modelTabel = new DefaultTableModel(
            new String[]{"ID", "Nama", "Jabatan", "Departemen", "Tgl Masuk", "Status"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        tabel = new JTable(modelTabel);
        tabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabel.setRowHeight(25);
        tabel.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabel.getTableHeader().setBackground(BIRU);
        tabel.getTableHeader().setForeground(Color.WHITE);

        add(new JScrollPane(tabel), BorderLayout.CENTER);
    }
}
