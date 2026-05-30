import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class PanelKaryawanAktif extends JPanel {

    private KaryawanController karyawanController;
    private JTable tabel;
    private DefaultTableModel modelTabel;

    private JTextField txtId, txtNama, txtKodeJabatan, txtNamaJabatan, txtDepartemen, txtTanggalMasuk;

    private static final Color BIRU   = new Color(25, 118, 210);
    private static final Color MERAH  = new Color(198, 40, 40);
    private static final Color ORANGE = new Color(245, 124, 0);

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

        tabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int baris = tabel.getSelectedRow();
                if (baris >= 0) {
                    txtId.setText(modelTabel.getValueAt(baris, 0).toString());
                    txtNama.setText(modelTabel.getValueAt(baris, 1).toString());
                    txtKodeJabatan.setText("");
                    txtNamaJabatan.setText(modelTabel.getValueAt(baris, 2).toString());
                    txtDepartemen.setText(modelTabel.getValueAt(baris, 3).toString());
                    txtTanggalMasuk.setText(modelTabel.getValueAt(baris, 4).toString());
                }
            }
        });

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Form Karyawan Aktif"));
        formPanel.setPreferredSize(new Dimension(320, 0));

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

        formPanel.add(new JLabel("Tanggal Masuk:"));
        txtTanggalMasuk = new JTextField();
        txtTanggalMasuk.setToolTipText("Format: DD-MM-YYYY");
        formPanel.add(txtTanggalMasuk);

        JPanel btnPanel = new JPanel(new GridLayout(4, 1, 6, 6));
        JButton btnTambah = buatTombol("Tambah", BIRU);
        JButton btnEdit   = buatTombol("Edit", ORANGE);
        JButton btnHapus  = buatTombol("Hapus", MERAH);
        JButton btnClear  = buatTombol("Clear", Color.GRAY);

        btnTambah.addActionListener(e -> tambah());
        btnEdit.addActionListener(e -> edit());
        btnHapus.addActionListener(e -> hapus());
        btnClear.addActionListener(e -> clear());

        btnPanel.add(btnTambah);
        btnPanel.add(btnEdit);
        btnPanel.add(btnHapus);
        btnPanel.add(btnClear);

        formPanel.add(new JLabel());
        formPanel.add(btnPanel);

        add(new JScrollPane(tabel), BorderLayout.CENTER);
        add(formPanel, BorderLayout.EAST);
    }

    private void tambah() {
        try {
            String id      = txtId.getText().trim();
            String nama    = txtNama.getText().trim();
            String kode    = txtKodeJabatan.getText().trim();
            String namaJab = txtNamaJabatan.getText().trim();
            String dept    = txtDepartemen.getText().trim();
            String tgl     = txtTanggalMasuk.getText().trim();

            if (id.isEmpty() || nama.isEmpty() || kode.isEmpty() ||
                namaJab.isEmpty() || dept.isEmpty() || tgl.isEmpty())
                throw new IllegalArgumentException("Semua field harus diisi!");

            Jabatan jabatan = new Jabatan(kode, namaJab, dept);
            KaryawanAktif k = new KaryawanAktif(id, nama, jabatan, tgl);
            karyawanController.tambahAktif(k);
            refresh();
            clear();
            JOptionPane.showMessageDialog(this, "Karyawan berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validasi Error", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void edit() {
        try {
            String id      = txtId.getText().trim();
            String nama    = txtNama.getText().trim();
            String kode    = txtKodeJabatan.getText().trim();
            String namaJab = txtNamaJabatan.getText().trim();
            String dept    = txtDepartemen.getText().trim();
            String tgl     = txtTanggalMasuk.getText().trim();

            if (id.isEmpty())
                throw new IllegalArgumentException("Pilih karyawan yang ingin diedit!");
            if (nama.isEmpty() || namaJab.isEmpty() || dept.isEmpty() || tgl.isEmpty())
                throw new IllegalArgumentException("Semua field harus diisi!");

            if (kode.isEmpty()) kode = "JB00";
            Jabatan jabatan    = new Jabatan(kode, namaJab, dept);
            KaryawanAktif baru = new KaryawanAktif(id, nama, jabatan, tgl);
            karyawanController.editAktif(id, baru);
            refresh();
            clear();
            JOptionPane.showMessageDialog(this, "Data berhasil diubah!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validasi Error", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hapus() {
        try {
            String id = txtId.getText().trim();
            if (id.isEmpty())
                throw new IllegalArgumentException("Pilih karyawan yang ingin dihapus!");

            int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Yakin hapus karyawan ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                karyawanController.hapusAktif(id);
                refresh();
                clear();
                JOptionPane.showMessageDialog(this, "Karyawan berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validasi Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void refresh() {
        modelTabel.setRowCount(0);
        for (KaryawanAktif k : karyawanController.getDaftarAktif()) {
            modelTabel.addRow(new Object[]{
                k.getIdKaryawan(),
                k.getNama(),
                k.getJabatan().getNamaJabatan(),
                k.getJabatan().getDepartemen(),
                k.getTanggalMasuk(),
                k.getStatus()
            });
        }
    }

    private void clear() {
        txtId.setText("");
        txtNama.setText("");
        txtKodeJabatan.setText("");
        txtNamaJabatan.setText("");
        txtDepartemen.setText("");
        txtTanggalMasuk.setText("");
    }

    private JButton buatTombol(String teks, Color bg) {
        JButton btn = new JButton(teks);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}