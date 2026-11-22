import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Theme {
    public static final Color BUTTER_YELLOW = new Color(254, 249, 231);

    public static final Font JUDUL = new Font("Arial", Font.BOLD, 20);
    public static final Font SUBJUDUL = new Font("Arial", Font.PLAIN, 13);
    public static final Font TEBAL = new Font("Arial", Font.BOLD, 18);

    public static ImageIcon getLogo() {
        return new ImageIcon("assets/logo.png");
    }
}
