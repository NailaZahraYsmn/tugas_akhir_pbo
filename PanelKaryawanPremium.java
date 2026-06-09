import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class PanelKaryawanPremium extends JPanel {
    private KaryawanPremiumController controller;

    private JTable tabel;
    private DefaultTableModel modelTabel;

    private JTextField txtId, txtNama, txtKodeJabatan, txtNamaJabatan;
    private JTextField txtDepartemen, txtTanggalMasuk, txtTunjangan;
    private JComboBox<String> cmbLevel;

    private static final Color BIRU   = new Color(25, 118, 210);
    private static final Color MERAH  = new Color(198, 40, 40);
    private static final Color UNGU   = new Color(102, 51, 153);
    private static final Color ORANGE = new Color(245, 124, 0);
    private static final Color HIJAU  = new Color(27, 94, 32);

    public PanelKaryawanPremium(KaryawanPremiumController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        modelTabel = new DefaultTableModel(
            new String[]{"ID", "Nama", "Jabatan", "Departemen", "Tgl Masuk", "Level", "Tunjangan", "Status"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        tabel = new JTable(modelTabel);
        tabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabel.setRowHeight(25);
        tabel.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabel.getTableHeader().setBackground(UNGU);
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
                    cmbLevel.setSelectedItem(modelTabel.getValueAt(baris, 5).toString());
                    txtTunjangan.setText(modelTabel.getValueAt(baris, 6).toString());
                }
            }
        });


        JPanel formPanel = new JPanel(new GridLayout(11, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Form Karyawan Premium"));
        formPanel.setPreferredSize(new Dimension(280, 0));

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

        formPanel.add(new JLabel("Level Premium:"));
        cmbLevel = new JComboBox<>(new String[]{"Senior", "Expert", "Master"});
        formPanel.add(cmbLevel);

        formPanel.add(new JLabel("Tunjangan (Rp):"));
        txtTunjangan = new JTextField();
        txtTunjangan.setToolTipText("Minimal Rp 1.000.000");
        formPanel.add(txtTunjangan);


        JPanel btnPanel = new JPanel(new GridLayout(6, 1, 6, 6));
        JButton btnTambah = buatTombol("Tambah", BIRU);
        JButton btnEdit   = buatTombol("Edit", ORANGE);
        JButton btnHapus  = buatTombol("Hapus", MERAH);
        JButton btnFilter = buatTombol("Filter by Level", UNGU);
        JButton btnSort   = buatTombol("Sort by Tunjangan", HIJAU);
        JButton btnClear  = buatTombol("Clear", Color.GRAY);

        btnTambah.addActionListener(e -> tambah());
        btnEdit.addActionListener(e -> edit());
        btnHapus.addActionListener(e -> hapus());
        btnFilter.addActionListener(e -> filter());
        btnSort.addActionListener(e -> sort());
        btnClear.addActionListener(e -> clear());

        btnPanel.add(btnTambah);
        btnPanel.add(btnEdit);
        btnPanel.add(btnHapus);
        btnPanel.add(btnFilter);
        btnPanel.add(btnSort);
        btnPanel.add(btnClear);

        formPanel.add(new JLabel());
        formPanel.add(btnPanel);

        add(new JScrollPane(tabel), BorderLayout.CENTER);
        add(formPanel, BorderLayout.EAST);
    }

    private void tambah() {
        try {
            String id           = txtId.getText().trim();
            String nama         = txtNama.getText().trim();
            String kode         = txtKodeJabatan.getText().trim();
            String namaJab      = txtNamaJabatan.getText().trim();
            String dept         = txtDepartemen.getText().trim();
            String tgl          = txtTanggalMasuk.getText().trim();
            String level        = cmbLevel.getSelectedItem().toString();
            String tunjanganStr = txtTunjangan.getText().trim();

            if (id.isEmpty() || nama.isEmpty() || kode.isEmpty() ||
                namaJab.isEmpty() || dept.isEmpty() || tgl.isEmpty() || tunjanganStr.isEmpty())
                throw new PremiumException("Semua field harus diisi!");

            double tunjangan = Double.parseDouble(tunjanganStr);
            if (tunjangan < 0)
                throw new PremiumException("Tunjangan tidak boleh negatif!");
            if (tunjangan < 1000000)
                throw new PremiumException("Tunjangan karyawan premium minimal Rp 1.000.000!");

            Jabatan jabatan   = new Jabatan(kode, namaJab, dept);
            KaryawanPremium k = new KaryawanPremium(id, nama, jabatan, tgl, tunjangan, level);
            controller.tambah(k);
            refresh(controller.getDaftar());
            clear();
            JOptionPane.showMessageDialog(this, "Karyawan Premium berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

        } catch (PremiumException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Premium Error", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tunjangan harus berupa angka!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void edit() {
        try {
            String id           = txtId.getText().trim();
            String nama         = txtNama.getText().trim();
            String kode         = txtKodeJabatan.getText().trim();
            String namaJab      = txtNamaJabatan.getText().trim();
            String dept         = txtDepartemen.getText().trim();
            String tgl          = txtTanggalMasuk.getText().trim();
            String level        = cmbLevel.getSelectedItem().toString();
            String tunjanganStr = txtTunjangan.getText().trim();

            if (id.isEmpty())
                throw new PremiumException("Pilih karyawan yang ingin diedit!");
            if (nama.isEmpty() || namaJab.isEmpty() || dept.isEmpty() ||
                tgl.isEmpty() || tunjanganStr.isEmpty())
                throw new PremiumException("Semua field harus diisi!");

            double tunjangan = Double.parseDouble(tunjanganStr);
            if (tunjangan < 0)
                throw new PremiumException("Tunjangan tidak boleh negatif!");
            if (tunjangan < 1000000)
                throw new PremiumException("Tunjangan karyawan premium minimal Rp 1.000.000!");

            if (kode.isEmpty()) kode = "JB00";
            Jabatan jabatan      = new Jabatan(kode, namaJab, dept);
            KaryawanPremium baru = new KaryawanPremium(id, nama, jabatan, tgl, tunjangan, level);
            controller.edit(id, baru);
            refresh(controller.getDaftar());
            clear();
            JOptionPane.showMessageDialog(this, "Data berhasil diubah!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

        } catch (PremiumException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Premium Error", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tunjangan harus berupa angka!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hapus() {
        try {
            String id = txtId.getText().trim();
            if (id.isEmpty())
                throw new PremiumException("Pilih karyawan yang ingin dihapus!");

            int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Yakin hapus karyawan premium ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                controller.hapus(id);
                refresh(controller.getDaftar());
                clear();
                JOptionPane.showMessageDialog(this, "Karyawan berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (PremiumException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Premium Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ===== FILTER =====
    private void filter() {
        String level = cmbLevel.getSelectedItem().toString();
        refresh(controller.filterByLevel(level));
        JOptionPane.showMessageDialog(this, "Menampilkan karyawan level: " + level, "Filter", JOptionPane.INFORMATION_MESSAGE);
    }

    private void sort() {
        refresh(controller.sortByTunjangan());
        JOptionPane.showMessageDialog(this, "Data diurutkan berdasarkan tunjangan tertinggi!", "Sorting", JOptionPane.INFORMATION_MESSAGE);
    }

    private void refresh(java.util.ArrayList<KaryawanPremium> daftar) {
        modelTabel.setRowCount(0);
        for (KaryawanPremium k : daftar) {
            modelTabel.addRow(new Object[]{
                k.getIdKaryawan(),
                k.getNama(),
                k.getJabatan().getNamaJabatan(),
                k.getJabatan().getDepartemen(),
                k.getTanggalMasuk(),
                k.getLevelPremium(),
                k.getTunjangan(),
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
        txtTunjangan.setText("");
        cmbLevel.setSelectedIndex(0);
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