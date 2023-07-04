import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Transfer extends JFrame{
    public JPanel transaksiPanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JButton kembaliButton;
    private JButton sudahButton;
    private JLabel label5;
    private JLabel label6;
    private String tipeProperti;
    private String namaProperti;
    private String mulai;
    private String selesai;
    private String rekeningBank;
    private String namaBank;

    public Transfer(String tipePropertix, String namaPropertix, String mulaix, String selesaix, String rekeningBankx, String namaBankx) {
        setTitle("CEPATLAH TRANSFER !!!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,350);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        this.tipeProperti = tipePropertix;
        this.namaProperti = namaPropertix;
        this.mulai = mulaix;
        this.selesai = selesaix;
        this.rekeningBank = rekeningBankx;
        this.namaBank = namaBankx;

        label2.setText(namaBank);
        label4.setText(rekeningBank);
        label6.setText(SewaProperti.formattedValue);

        sudahButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection koneksi = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);

                    String query = "{call menyewa(?, ?, ?, ?, ?, ?)}";
                    CallableStatement statement = koneksi.prepareCall(query);

                    // Mengatur nilai parameter
                    statement.setString(1, Login.username123);
                    statement.setString(2, tipeProperti);
                    statement.setString(3, namaProperti);
                    statement.setString(4, mulai);
                    statement.setString(5, selesai);
                    statement.setString(6, rekeningBank);

                    statement.execute();
                    statement.close();
                    koneksi.close();
                } catch (SQLException a) {
                    a.printStackTrace();
                }

                SwingUtilities.invokeLater(() -> {
                    Opsi pilihan = new Opsi();
                    pilihan.setContentPane(pilihan.opsiPanel);
                });

                // Mengatur ulang tampilan JFrame
                revalidate();
                repaint();
                dispose();
            }
        });

        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    SewaProperti sewa = new SewaProperti();
                    sewa.setContentPane(sewa.SPPanel);
                });

                // Mengatur ulang tampilan JFrame
                revalidate();
                repaint();
                dispose();
            }
        });
    }
}
