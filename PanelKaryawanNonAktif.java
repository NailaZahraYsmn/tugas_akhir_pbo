import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class PanelKaryawanNonAktif extends JPanel {

    private KaryawanController karyawanController;
    private JTable tabel;
    private DefaultTableModel modelTabel;

    private JTextField txtId, txtNama, txtKodeJabatan, txtNamaJabatan, txtDepartemen, txtTanggalKeluar, txtKeterangan;

    private static final Color MERAH  = new Color(198, 40, 40);
    private static final Color BIRU   = new Color(25, 118, 210);

    public PanelKaryawanNonAktif(KaryawanController karyawanController) {
        this.karyawanController = karyawanController;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        modelTabel = new DefaultTableModel(
            new String[]{"ID", "Nama", "Jabatan", "Departemen", "Tgl Keluar", "Status", "Keterangan"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        tabel = new JTable(modelTabel);
        tabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabel.setRowHeight(25);
        tabel.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabel.getTableHeader().setBackground(MERAH);
        tabel.getTableHeader().setForeground(Color.WHITE);

        tabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int baris = tabel.getSelectedRow();
                if (baris >= 0) {
                    txtId.setText(modelTabel.getValueAt(baris, 0).toString());
                    txtNama.setText(modelTabel.getValueAt(baris, 1).toString());
                    txtKodeJabatan.setText("");
                    txtNamaJabatan.setText(modelTabel.getValueAt(baris, 2).toString());
                    txtDepartemen.setText(modelTabel.getValueAt(baris, 3).toString());
                    txtTanggalKeluar.setText(modelTabel.getValueAt(baris, 4).toString());
                    txtKeterangan.setText(modelTabel.getValueAt(baris, 6).toString());
                }
            }
        });

        JPanel formPanel = new JPanel(new GridLayout(9, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Form Karyawan Non-Aktif"));
        formPanel.setPreferredSize(new Dimension(260, 0));

        formPanel.add(new JLabel("ID Karyawan:"));
        txtId = new JTextField();
        formPanel.add(txtId);

        formPanel.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        formPanel.add(txtNama);

        formPanel.add(new JLabel("Kode Jabatan:"));
        txtKodeJabatan = new JTextField();
        txtKodeJabatan.setToolTipText("Contoh: JB01");
        formPanel.add(txtKodeJabatan);

        formPanel.add(new JLabel("Nama Jabatan:"));
        txtNamaJabatan = new JTextField();
        formPanel.add(txtNamaJabatan);

        formPanel.add(new JLabel("Departemen:"));
        txtDepartemen = new JTextField();
        formPanel.add(txtDepartemen);

        formPanel.add(new JLabel("Tanggal Keluar:"));
        txtTanggalKeluar = new JTextField();
        txtTanggalKeluar.setToolTipText("Format: DD-MM-YYYY");
        formPanel.add(txtTanggalKeluar);

        formPanel.add(new JLabel("Keterangan:"));
        txtKeterangan = new JTextField();
        formPanel.add(txtKeterangan);

        add(new JScrollPane(tabel), BorderLayout.CENTER);
        add(formPanel, BorderLayout.EAST);
    }
}