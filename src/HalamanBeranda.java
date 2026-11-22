import java.awt.*;
import javax.swing.*;

public class HalamanBeranda extends JPanel {
    private GridBagLayout layoutUtama;
    private FlowLayout layoutTombol;

    private JPanel panelTombol;

    private ImageIcon iconLogo;
    private JLabel labelLogo;
    private JLabel labelJudul;
    private JLabel labelDeskripsi;

    private JButton tombolDaftar;
    private JButton tombolLihat;
    private MainFrame frame;

    public HalamanBeranda(MainFrame frame) {
        this.frame = frame;
        setBackground(Theme.BUTTER_YELLOW);
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        layoutUtama = new GridBagLayout();
        layoutTombol = new FlowLayout(FlowLayout.CENTER, 15, 0);

        setLayout(layoutUtama);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // 1. Logo
        iconLogo = Theme.getLogo();
        labelLogo = new JLabel(iconLogo);
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 8, 0);
        add(labelLogo, gbc);

        // 2. Judul
        labelJudul = new JLabel("PawCare Pet Hotel");
        labelJudul.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 4, 0);
        add(labelJudul, gbc);

        // 3. Deskripsi
        labelDeskripsi = new JLabel("Layanan Penitipan Hewan", JLabel.CENTER);
        labelDeskripsi.setPreferredSize(new Dimension(280, 20));
        labelDeskripsi.setFont(new Font("Arial", Font.PLAIN, 13));
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 16, 0);
        add(labelDeskripsi, gbc);

        // 4. Panel Tombol
        panelTombol = new JPanel();
        panelTombol.setLayout(layoutTombol);
        panelTombol.setBackground(Theme.BUTTER_YELLOW);

        tombolDaftar = new JButton("Daftar Penitipan");
        tombolLihat = new JButton("Lihat Daftar Hewan");

        tombolDaftar.setFocusPainted(false);
        tombolLihat.setFocusPainted(false);

        tombolDaftar.addActionListener(e -> this.frame.bukaBooking());
        tombolLihat.addActionListener(e -> this.frame.bukaDaftar());

        panelTombol.add(tombolDaftar);
        panelTombol.add(tombolLihat);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(panelTombol, gbc);
    }
}
