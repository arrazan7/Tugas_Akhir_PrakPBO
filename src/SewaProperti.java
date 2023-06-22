import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class SewaProperti extends JFrame{
    public JPanel SPPanel;
    private JLabel headerSP;
    private JTextField mulaiTF;
    private JLabel tipeProperti;
    private JLabel namaProperti;
    private JLabel mulaiSewa;
    private JLabel tfBank;
    private JButton selesaiButton;
    private JButton kembaliButton;
    private JRadioButton BNIRadioButton;
    private JRadioButton BRIRadioButton;
    private JRadioButton BSIRadioButton;
    private JRadioButton MANDIRIRadioButton;
    private JRadioButton BTNRadioButton;
    private JTextField selesaiTF;
    private JLabel selesaiSewa;
    private JLabel tglMulai;
    private JLabel tglSelesai;
    private JTextField tipeTF;
    private JTextField namaTF;


    // Koneksi ke database
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public SewaProperti() {
        setTitle("Formulir Sewa Properti");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,300);
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


                String tipeProperti = tipeTF.getText();
                String namaProperti = namaTF.getText();
                String mulai = mulaiTF.getText();
                String selesai = selesaiTF.getText();
                String rekeningBank = null;

                if (BNIRadioButton.isSelected()) {
                    rekeningBank = "5544332211";
                }
                else if (BRIRadioButton.isSelected()) {
                    rekeningBank = "1122334455";
                }
                else if (BSIRadioButton.isSelected()) {
                    rekeningBank = "1113335557";
                }
                else if (BTNRadioButton.isSelected()) {
                    rekeningBank = "7659234728";
                }
                else if (MANDIRIRadioButton.isSelected()) {
                    rekeningBank = "9876543210";
                }

                try {
                    Connection koneksi = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);

                    String query = "{call menyewa(?, ?, ?, ?, ?, ?)}";
                    CallableStatement statement = koneksi.prepareCall(query);

                    // Mengatur nilai parameter
                    statement.setString(1, "fayy");
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
            }
        });
    }


    public static void main(String[] args) {
        SewaProperti coba = new SewaProperti();
        coba.setContentPane(coba.SPPanel);
    }
}
