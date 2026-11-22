import java.awt.*;
import javax.swing.*;

public class BookingPanel extends JPanel {
    private BorderLayout layoutUtama;
    private GridLayout layoutForm;
    private FlowLayout layoutRadio;
    private FlowLayout layoutTombol;

    private JPanel panelForm;
    private JPanel panelRadio;
    private JPanel panelTombol;

    private JLabel labelJudul;
    private JLabel labelPemilik;
    private JLabel labelHewan;
    private JLabel labelJenis;
    private JLabel labelPaket;
    private JLabel labelDurasi;
    private JLabel labelTotalJudul;
    private JLabel labelTotal;

    private JTextField textPemilik;
    private JTextField textHewan;
    private JTextField textDurasi;
    private JComboBox<String> comboJenis;
    private JRadioButton radioTitip;
    private JRadioButton radioGrooming;
    private JRadioButton radioKomplit;
    private ButtonGroup grupPaket;

    private JButton tombolHitung;
    private JButton tombolSimpan;
    private JButton tombolKembali;

    private MainFrame frame;
    private DaftarHewanPanel daftarPanel;
    private int counter = 4;

    public BookingPanel(MainFrame frame, DaftarHewanPanel daftarPanel) {
        this.frame = frame;
        this.daftarPanel = daftarPanel;

        setBackground(Theme.BUTTER_YELLOW);
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        layoutUtama = new BorderLayout(10, 10);
        layoutForm = new GridLayout(6, 2, 8, 8);
        layoutRadio = new FlowLayout(FlowLayout.LEFT, 4, 0);
        layoutTombol = new FlowLayout(FlowLayout.CENTER, 10, 8);

        setLayout(layoutUtama);

        labelJudul = new JLabel("Formulir Penitipan Hewan", SwingConstants.CENTER);
        labelJudul.setFont(new Font("Arial", Font.BOLD, 18));

        panelForm = new JPanel();
        panelForm.setLayout(layoutForm);
        panelForm.setBackground(Theme.BUTTER_YELLOW);

        labelPemilik = new JLabel("Nama Pemilik:");
        textPemilik = new JTextField();

        labelHewan = new JLabel("Nama Hewan:");
        textHewan = new JTextField();

        labelJenis = new JLabel("Jenis Hewan:");
        comboJenis = new JComboBox<>(new String[]{"Kucing", "Anjing", "Kelinci"});

        labelPaket = new JLabel("Paket Layanan:");
        radioTitip = new JRadioButton("Penitipan", true);
        radioGrooming = new JRadioButton("Grooming");
        radioKomplit = new JRadioButton("Komplit");

        radioTitip.setOpaque(false);
        radioGrooming.setOpaque(false);
        radioKomplit.setOpaque(false);

        grupPaket = new ButtonGroup();
        grupPaket.add(radioTitip);
        grupPaket.add(radioGrooming);
        grupPaket.add(radioKomplit);

        panelRadio = new JPanel();
        panelRadio.setLayout(layoutRadio);
        panelRadio.setOpaque(false);
        panelRadio.add(radioTitip);
        panelRadio.add(radioGrooming);
        panelRadio.add(radioKomplit);

        labelDurasi = new JLabel("Durasi (Hari):");
        textDurasi = new JTextField("3");

        labelTotalJudul = new JLabel("Total Biaya:");
        labelTotal = new JLabel("Rp120.000");
        labelTotal.setFont(new Font("Arial", Font.BOLD, 13));

        panelForm.add(labelPemilik);
        panelForm.add(textPemilik);
        panelForm.add(labelHewan);
        panelForm.add(textHewan);
        panelForm.add(labelJenis);
        panelForm.add(comboJenis);
        panelForm.add(labelPaket);
        panelForm.add(panelRadio);
        panelForm.add(labelDurasi);
        panelForm.add(textDurasi);
        panelForm.add(labelTotalJudul);
        panelForm.add(labelTotal);

        panelTombol = new JPanel();
        panelTombol.setLayout(layoutTombol);
        panelTombol.setBackground(Theme.BUTTER_YELLOW);

        tombolKembali = new JButton("Kembali");
        tombolHitung = new JButton("Hitung Total");
        tombolSimpan = new JButton("Simpan");

        tombolKembali.setFocusPainted(false);
        tombolHitung.setFocusPainted(false);
        tombolSimpan.setFocusPainted(false);

        tombolKembali.addActionListener(e -> this.frame.bukaBeranda());
        tombolHitung.addActionListener(e -> hitungTotal());
        tombolSimpan.addActionListener(e -> simpanPesanan());

        panelTombol.add(tombolKembali);
        panelTombol.add(tombolHitung);
        panelTombol.add(tombolSimpan);

        add(labelJudul, BorderLayout.NORTH);
        add(panelForm, BorderLayout.CENTER);
        add(panelTombol, BorderLayout.SOUTH);
    }

    public void hitungTotal() {
        String jenis = (String) comboJenis.getSelectedItem();
        String paket = radioTitip.isSelected() ? "Penitipan" : (radioGrooming.isSelected() ? "Grooming" : "Komplit");
        int durasi = 1;
        try {
            durasi = Integer.parseInt(textDurasi.getText().trim());
        } catch (Exception ignored) {}

        int tarifHarian = 40000;
        if ("Anjing".equalsIgnoreCase(jenis)) tarifHarian = 60000;
        if ("Kelinci".equalsIgnoreCase(jenis)) tarifHarian = 30000;

        int total = tarifHarian * durasi;
        if ("Grooming".equalsIgnoreCase(paket)) total += 35000;
        if ("Komplit".equalsIgnoreCase(paket)) total += 50000;

        labelTotal.setText("Rp" + String.format("%,d", total).replace(',', '.'));
    }

    public void simpanPesanan() {
        String pemilik = textPemilik.getText().trim();
        String hewan = textHewan.getText().trim();

        if (pemilik.isEmpty() || hewan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama pemilik dan hewan harus diisi!", "Perhatian", JOptionPane.WARNING_MESSAGE);
            return;
        }

        hitungTotal();

        String id = "PET-00" + counter++;
        String jenis = (String) comboJenis.getSelectedItem();
        String paket = radioTitip.isSelected() ? "Penitipan" : (radioGrooming.isSelected() ? "Grooming" : "Komplit");
        String durasi = textDurasi.getText().trim() + " Hari";
        String total = labelTotal.getText();

        daftarPanel.tambahBaris(new Object[]{id, pemilik, hewan, jenis, paket, durasi, total});

        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!\nID: " + id, "Sukses", JOptionPane.INFORMATION_MESSAGE);
        frame.bukaDaftar();
    }

    public void resetForm() {
        textPemilik.setText("");
        textHewan.setText("");
        comboJenis.setSelectedIndex(0);
        radioTitip.setSelected(true);
        textDurasi.setText("3");
        labelTotal.setText("Rp120.000");
    }
}
