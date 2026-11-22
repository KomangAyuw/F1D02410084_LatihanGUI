import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DaftarHewanPanel extends JPanel {
    private BorderLayout layoutUtama;
    private FlowLayout layoutTombol;

    private JLabel labelJudul;
    private JTable tabelData;
    private DefaultTableModel modelTabel;
    private JScrollPane scrollTabel;

    private JPanel panelTombol;
    private JButton tombolKembali;
    private JButton tombolTambah;
    private JButton tombolHapus;
    private MainFrame frame;

    public DaftarHewanPanel(MainFrame frame) {
        this.frame = frame;
        setBackground(Theme.BUTTER_YELLOW);
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        layoutUtama = new BorderLayout(10, 10);
        layoutTombol = new FlowLayout(FlowLayout.CENTER, 10, 8);

        setLayout(layoutUtama);

        labelJudul = new JLabel("Daftar Hewan Dititipkan", SwingConstants.CENTER);
        labelJudul.setFont(new Font("Arial", Font.BOLD, 18));

        String[] kolom = {"ID", "Pemilik", "Nama Hewan", "Jenis", "Paket", "Durasi", "Total Biaya"};
        modelTabel = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelData = new JTable(modelTabel);
        tabelData.setRowHeight(22);
        scrollTabel = new JScrollPane(tabelData);
        scrollTabel.getViewport().setBackground(Color.WHITE);

        panelTombol = new JPanel();
        panelTombol.setLayout(layoutTombol);
        panelTombol.setBackground(Theme.BUTTER_YELLOW);

        tombolKembali = new JButton("Kembali");
        tombolTambah = new JButton("Tambah Penitipan");
        tombolHapus = new JButton("Hapus Data");

        tombolKembali.setFocusPainted(false);
        tombolTambah.setFocusPainted(false);
        tombolHapus.setFocusPainted(false);

        tombolKembali.addActionListener(e -> this.frame.bukaBeranda());
        tombolTambah.addActionListener(e -> this.frame.bukaBooking());
        tombolHapus.addActionListener(e -> hapusData());

        panelTombol.add(tombolKembali);
        panelTombol.add(tombolTambah);
        panelTombol.add(tombolHapus);

        add(labelJudul, BorderLayout.NORTH);
        add(scrollTabel, BorderLayout.CENTER);
        add(panelTombol, BorderLayout.SOUTH);

        isiDataAwal();
    }

    public void tambahBaris(Object[] baris) {
        modelTabel.addRow(baris);
    }

    public void hapusData() {
        int baris = tabelData.getSelectedRow();
        if (baris != -1) {
            String namaHewan = modelTabel.getValueAt(baris, 2).toString();
            int konfirmasi = JOptionPane.showConfirmDialog(
                this, 
                "Yakin ingin menghapus data hewan '" + namaHewan + "'?", 
                "Konfirmasi Hapus", 
                JOptionPane.YES_NO_OPTION
            );
            if (konfirmasi == JOptionPane.YES_OPTION) {
                modelTabel.removeRow(baris);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void isiDataAwal() {
        tambahBaris(new Object[]{"PET-001", "Komang Ayu", "Milo", "Kucing", "Komplit", "3 Hari", "Rp170.000"});
        tambahBaris(new Object[]{"PET-002", "Budi Santoso", "Bleki", "Anjing", "Penitipan", "5 Hari", "Rp300.000"});
        tambahBaris(new Object[]{"PET-003", "Siti Rahma", "Snowy", "Kelinci", "Penitipan", "2 Hari", "Rp60.000"});
    }
}
