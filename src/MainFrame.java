import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelUtama;
    private HalamanBeranda halamanBeranda;
    private BookingPanel bookingPanel;
    private DaftarHewanPanel daftarPanel;

    public MainFrame() {
        cardLayout = new CardLayout();
        panelUtama = new JPanel();
        panelUtama.setLayout(cardLayout);
        panelUtama.setBackground(Theme.BUTTER_YELLOW);

        halamanBeranda = new HalamanBeranda(this);
        daftarPanel = new DaftarHewanPanel(this);
        bookingPanel = new BookingPanel(this, daftarPanel);

        panelUtama.add(halamanBeranda, "beranda");
        panelUtama.add(bookingPanel, "booking");
        panelUtama.add(daftarPanel, "daftar");

        setTitle("PawCare - Penitipan Hewan");
        setSize(560, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelUtama);
    }

    public void bukaBeranda() {
        cardLayout.show(panelUtama, "beranda");
    }

    public void bukaBooking() {
        bookingPanel.resetForm();
        cardLayout.show(panelUtama, "booking");
    }

    public void bukaDaftar() {
        cardLayout.show(panelUtama, "daftar");
    }

    public static void main(String[] args) {
        MainFrame aplikasi = new MainFrame();
        aplikasi.setVisible(true);
    }
}
