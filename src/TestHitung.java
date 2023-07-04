import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestHitung extends JFrame {
    private JPanel panel1;
    private JTextField mulaiTF;
    private JTextField selesaiTF;
    private JLabel hasilLabel;
    private JLabel hasil2Label;

    public TestHitung() {
        setTitle("Test Hitung Tanggal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        // Menggunakan DocumentListener untuk memantau perubahan pada JTextField
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hitungSelisih();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                hitungSelisih();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                hitungSelisih();
            }
        };

        mulaiTF.getDocument().addDocumentListener(documentListener);
        selesaiTF.getDocument().addDocumentListener(documentListener);

        add(panel1);
    }

    private void hitungSelisih() {
        String mulaiText = mulaiTF.getText();
        String selesaiText = selesaiTF.getText();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        try {
            Date mulaiDate = dateFormat.parse(mulaiText);
            Date selesaiDate = dateFormat.parse(selesaiText);

            if (mulaiDate.compareTo(selesaiDate) >= 0) {
                hasilLabel.setText("Input Salah!");
                hasil2Label.setText("Tanggal Mulai <lebih besar atau sama dengan dari> Tanggal Selesai");
            } else {
                long selisihMillis = selesaiDate.getTime() - mulaiDate.getTime();
                long selisihHari = selisihMillis / (24 * 60 * 60 * 1000);

                hasilLabel.setText("Selisih Hari: " + selisihHari);
                hasil2Label.setText("");
            }
        } catch (ParseException e) {
            hasilLabel.setText("Tanggal Tidak Valid");
            hasil2Label.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TestHitung();
        });
    }
}
