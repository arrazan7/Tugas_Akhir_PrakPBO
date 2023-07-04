import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class BeliProperti extends JFrame{
    public JPanel BPPanel;
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
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JLabel hargaLabel;
    private JLabel hasilLabel;
    private long hargaJual;
    public static String formattedValue;


    public BeliProperti() {
        setTitle("Formulir Beli Properti");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        try {
            Connection koneksi = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);
            Statement statement = koneksi.createStatement();

            // Menjalankan query
            String query = "SELECT DISTINCT tipe_properti FROM properti";
            ResultSet resultSet = statement.executeQuery(query);

            // Mengambil hasil query dan memasukkannya ke dalam List
            List<String> tipePropertiList = new ArrayList<>();
            while (resultSet.next()) {
                String tipeProperti = resultSet.getString("tipe_properti");
                tipePropertiList.add(tipeProperti);
            }

            // Mengisi model ComboBox dengan data dari List
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(tipePropertiList.toArray(new String[0]));
            comboBox1.setModel(comboBoxModel);

            // Menutup koneksi dan resource
            resultSet.close();
            statement.close();
            koneksi.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        comboBox1.addActionListener(e -> {
            String selectedTipe = (String) comboBox1.getSelectedItem();
            if (selectedTipe != null) {
                try {
                    // Menjalankan query sesuai dengan pilihan comboBox1
                    Connection koneksi = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);
                    String query2 = "SELECT nama_properti FROM properti WHERE tipe_properti = ? AND status = 'TERSEDIA'";
                    PreparedStatement statement2 = koneksi.prepareStatement(query2);
                    statement2.setString(1, selectedTipe);
                    ResultSet resultSet2 = statement2.executeQuery();

                    // Mengambil hasil query dan memasukkannya ke dalam List
                    List<String> namaPropertiList = new ArrayList<>();
                    while (resultSet2.next()) {
                        String namaProperti = resultSet2.getString("nama_properti");
                        namaPropertiList.add(namaProperti);
                    }

                    // Mengisi model ComboBox comboBox2 dengan data dari List
                    DefaultComboBoxModel<String> comboBoxModel2 = new DefaultComboBoxModel<>(namaPropertiList.toArray(new String[0]));
                    comboBox2.setModel(comboBoxModel2);

                    // Menutup koneksi dan resource
                    resultSet2.close();
                    statement2.close();
                    koneksi.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        comboBox2.addActionListener(e -> {
            String selectedTipe2 = (String) comboBox2.getSelectedItem();
            if (selectedTipe2 != null) {
                try {
                    // Menjalankan query sesuai dengan pilihan comboBox2
                    Connection koneksi = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);
                    String query2 = "SELECT harga_jual FROM properti WHERE tipe_properti = ? AND nama_properti = ?";
                    PreparedStatement statement3 = koneksi.prepareStatement(query2);
                    String tipe = (String) comboBox1.getSelectedItem();
                    String nama = (String) comboBox2.getSelectedItem();

                    statement3.setString(1, tipe);
                    statement3.setString(2, nama);
                    ResultSet resultSet3 = statement3.executeQuery();

                    // Mengambil hasil query dan memasukkannya ke dalam variabel
                    while (resultSet3.next()) {
                        this.hargaJual = resultSet3.getLong("harga_jual");
                    }

                    // Membuat instance dari NumberFormat dengan format yang diinginkan
                    NumberFormat formatter = new DecimalFormat("#,###");

                    // Mengubah nilai long menjadi String dengan format yang diinginkan
                    this.formattedValue = formatter.format(hargaJual);

                    hasilLabel.setText(formattedValue);

                    // Menutup koneksi dan resource
                    resultSet3.close();
                    statement3.close();
                    koneksi.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

        selesaiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tipeProperti = (String) comboBox1.getSelectedItem();
                String namaProperti = (String) comboBox2.getSelectedItem();
                String rekeningBank;
                String namaBank;

                if (BNIRadioButton.isSelected()) {
                    rekeningBank = "5544332211";
                    namaBank = "BNI";
                }
                else if (BRIRadioButton.isSelected()) {
                    rekeningBank = "1122334455";
                    namaBank = "BRI";
                }
                else if (BSIRadioButton.isSelected()) {
                    rekeningBank = "1113335557";
                    namaBank = "BSI";
                }
                else if (BTNRadioButton.isSelected()) {
                    rekeningBank = "7659234728";
                    namaBank = "BTN";
                }
                else if (MANDIRIRadioButton.isSelected()) {
                    rekeningBank = "9876543210";
                    namaBank = "MANDIRI";
                } else {
                    namaBank = "nama";
                    rekeningBank = "rek";
                }

                SwingUtilities.invokeLater(() -> {
                    Transfer2 tf = new Transfer2(tipeProperti, namaProperti, rekeningBank, namaBank);
                    tf.setContentPane(tf.transaksi2Panel);
                });

                revalidate();
                repaint();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BeliProperti coba = new BeliProperti();
            coba.setContentPane(coba.BPPanel);
        });
    }
}
