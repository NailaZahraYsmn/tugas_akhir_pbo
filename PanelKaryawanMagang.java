import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class PanelKaryawanMagang extends JPanel {

    private KaryawanController karyawanController;
    private JTable tabel;
    private DefaultTableModel modelTabel;

    private JTextField txtId, txtNama, txtKodeJabatan, txtNamaJabatan,
                       txtDepartemen, txtTanggalMasuk, txtDurasi;

    private static final Color BIRU  = new Color(25, 118, 210);
    private static final Color MERAH = new Color(198, 40, 40);

    public PanelKaryawanMagang(KaryawanController karyawanController) {
        this.karyawanController = karyawanController;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        modelTabel = new DefaultTableModel(
            new String[]{"ID", "Nama", "Jabatan", "Departemen",
                         "Tgl Masuk", "Status", "Durasi (Bulan)"}, 0) {
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
                    txtDurasi.setText(modelTabel.getValueAt(baris, 8).toString());
                }
            }
        });

        JPanel formPanel = new JPanel(new GridLayout(11, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Form Karyawan Magang"));
        formPanel.setPreferredSize(new Dimension(340, 0));

        formPanel.add(new JLabel("ID Karyawan:"));
        txtId = new JTextField(); formPanel.add(txtId);

        formPanel.add(new JLabel("Nama:"));
        txtNama = new JTextField(); formPanel.add(txtNama);

        formPanel.add(new JLabel("Kode Jabatan:"));
        txtKodeJabatan = new JTextField();
        txtKodeJabatan.setToolTipText("Contoh: JB01");
        formPanel.add(txtKodeJabatan);

        formPanel.add(new JLabel("Nama Jabatan:"));
        txtNamaJabatan = new JTextField(); formPanel.add(txtNamaJabatan);

        formPanel.add(new JLabel("Departemen:"));
        txtDepartemen = new JTextField(); formPanel.add(txtDepartemen);

        formPanel.add(new JLabel("Tanggal Masuk:"));
        txtTanggalMasuk = new JTextField();
        txtTanggalMasuk.setToolTipText("Format: DD-MM-YYYY");
        formPanel.add(txtTanggalMasuk);

        formPanel.add(new JLabel("Durasi Magang (bulan):"));
        txtDurasi = new JTextField();
        txtDurasi.setToolTipText("Angka bulat, contoh: 3");
        formPanel.add(txtDurasi);

        JPanel btnPanel = new JPanel(new GridLayout(3, 1, 6, 6));
        JButton btnTambah = buatTombol("Tambah Magang", BIRU);
        JButton btnHapus  = buatTombol("Hapus", MERAH);
        JButton btnClear  = buatTombol("Clear", Color.GRAY);

        btnTambah.addActionListener(e -> tambah());
        btnHapus.addActionListener(e -> hapus());
        btnClear.addActionListener(e -> clear());

        btnPanel.add(btnTambah);
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
            String durasi  = txtDurasi.getText().trim();

            if (id.isEmpty() || nama.isEmpty() || kode.isEmpty() ||
                namaJab.isEmpty() || dept.isEmpty() || tgl.isEmpty())
                throw new IllegalArgumentException("Semua field harus diisi!");

            if (durasi.isEmpty())
                throw new InputTidakValidException("Durasi magang tidak boleh kosong.");

            int durasiInt;
            try {
                durasiInt = Integer.parseInt(durasi);
            } catch (NumberFormatException ex) {
                throw new InputTidakValidException("Durasi magang harus berupa angka bulat.");
            }

            Jabatan jabatan = new Jabatan(kode, namaJab, dept);
            KaryawanMagang km = new KaryawanMagang(
                id, nama, jabatan, tgl, durasiInt);

            karyawanController.tambahAktif(km);
            refresh();
            clear();
            JOptionPane.showMessageDialog(this,
                "Karyawan Magang berhasil ditambahkan!", "Sukses",
                JOptionPane.INFORMATION_MESSAGE);

        } catch (InputTidakValidException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                "Input Tidak Valid", JOptionPane.WARNING_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                "Validasi Error", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hapus() {
        try {
            String id = txtId.getText().trim();
            if (id.isEmpty())
                throw new IllegalArgumentException("Pilih karyawan yang ingin dihapus!");

            int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Yakin hapus karyawan magang ini?", "Konfirmasi",
                JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                karyawanController.hapusAktif(id);
                refresh();
                clear();
                JOptionPane.showMessageDialog(this,
                    "Karyawan Magang berhasil dihapus!", "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                "Validasi Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void refresh() {
        modelTabel.setRowCount(0);
        for (KaryawanAktif k : karyawanController.getDaftarAktif()) {
            if (k instanceof KaryawanMagang km) {
                modelTabel.addRow(new Object[]{
                    km.getIdKaryawan(),
                    km.getNama(),
                    km.getJabatan().getNamaJabatan(),
                    km.getJabatan().getDepartemen(),
                    km.getTanggalMasuk(),
                    km.getStatus(),
                    km.getDurasiMagang()
                });
            }
        }
    }

    private void clear() {
        txtId.setText("");          txtNama.setText("");
        txtKodeJabatan.setText(""); txtNamaJabatan.setText("");
        txtDepartemen.setText("");  txtTanggalMasuk.setText("");
        txtDurasi.setText("");
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