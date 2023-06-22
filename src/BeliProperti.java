import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BeliProperti extends JFrame{
    public JPanel BPPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JRadioButton BNIRadioButton;
    private JRadioButton BRIRadioButton;
    private JLabel headerBP;
    private JLabel tipeProperti;
    private JLabel namaProperti;
    private JLabel tfBank;
    private JRadioButton BSIRadioButton;
    private JRadioButton MANDIRIRadioButton;
    private JRadioButton BTNRadioButton;
    private JButton selesaiButton;
    private JButton kembaliButton;

    public BeliProperti() {
        setTitle("Formulir Beli Properti");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Membuat instance FormDaftar
                Opsi pilihan = new Opsi();
                // Menetapkan panel FormDaftar sebagai konten utama JFrame
                pilihan.setContentPane(pilihan.opsiPanel);
                // Mengatur ulang tampilan JFrame
                revalidate();
                repaint();
                dispose();
            }
        });

        selesaiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:oracle:thin:@localhost:1521:XE";
                String username = "C##PROYEKPBO";
                String password = "oracledatabase";

                // Membuat instance FormDaftar
                Transfer2 tf = new Transfer2();
                // Menetapkan panel FormDaftar sebagai konten utama JFrame
                tf.setContentPane(tf.transaksi2Panel);
                // Mengatur ulang tampilan JFrame
                revalidate();
                repaint();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        BeliProperti coba = new BeliProperti();
        coba.setContentPane(coba.BPPanel);
    }
}
