import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Transfer2 extends JFrame{
    public JPanel transaksi2Panel;
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
    private String rekeningBank;
    private String namaBank;

    public Transfer2(String tipePropertix, String namaPropertix, String rekeningBankx, String namaBankx) {
        setTitle("CEPATLAH TRANSFER !!!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,350);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        this.tipeProperti = tipePropertix;
        this.namaProperti = namaPropertix;
        this.rekeningBank = rekeningBankx;
        this.namaBank = namaBankx;

        label2.setText(namaBank);
        label4.setText(rekeningBank);
        label6.setText(BeliProperti.formattedValue);

        sudahButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection koneksi = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);
                    String query = "{call membeli(?, ?, ?, ?)}";
                    CallableStatement statement = koneksi.prepareCall(query);

                    // Mengatur nilai parameter
                    statement.setString(1, Login.username123);
                    statement.setString(2, tipeProperti);
                    statement.setString(3, namaProperti);
                    statement.setString(4, rekeningBank);

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
                    BeliProperti sewa = new BeliProperti();
                    sewa.setContentPane(sewa.BPPanel);
                });

                // Mengatur ulang tampilan JFrame
                revalidate();
                repaint();
                dispose();
            }
        });
    }
}
