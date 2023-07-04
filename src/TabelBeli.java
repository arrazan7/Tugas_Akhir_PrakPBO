import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class TabelBeli extends JFrame{
    public JPanel TBPanel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JTable tabelBeli;
    private JLabel userName;
    private JLabel currentDate;
    private JButton kembaliButton;
    private JScrollPane scrollTabel;
    private JLabel userLabel;
    private JLabel tglLabel;

    public TabelBeli() {
        setTitle("Tabel Beli Anda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
//        createTable();

        userLabel.setText(Login.username123);
        // Membuat objek SimpleDateFormat dengan format yang diinginkan
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        // Mengubah objek Date menjadi format teks
        String dateString = dateFormat.format(Login.tanggalSaatini);
        // Mengatur teks pada JLabel
        tglLabel.setText(dateString);

        try {
            // Menghubungkan ke database
            Connection koneksi = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);

            String query = "SELECT id_transaksi, id_properti, tipe_properti, nama_properti, harga, no_rekening, tanggal_beli FROM transaksi_beli WHERE username = ?";
            PreparedStatement statement = koneksi.prepareStatement(query);
            statement.setString(1, Login.username123);
            ResultSet resultSet = statement.executeQuery();

            // Mengambil metadata kolom dari ResultSet
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Membuat Vector untuk menyimpan nama kolom
            Vector<String> columnNames = new Vector<>();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // Membuat Vector untuk menyimpan data baris
            Vector<Vector<Object>> data = new Vector<>();
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int column = 1; column <= columnCount; column++) {
                    row.add(resultSet.getObject(column));
                }
                data.add(row);
            }

            // Membuat DefaultTableModel yang tidak dapat diedit dan mengatur kolom dan data
            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Mengembalikan false untuk membuat sel tidak dapat diedit
                }
            };
            tabelBeli.setModel(tableModel);

            // Menyesuaikan lebar kolom secara otomatis
            TableColumnModel columnModel = tabelBeli.getColumnModel();
            for (int column = 0; column < columnCount; column++) {
                int width = 15; // Lebar default
                for (int row = 0; row < data.size(); row++) {
                    TableCellRenderer renderer = tabelBeli.getCellRenderer(row, column);
                    Component comp = tabelBeli.prepareRenderer(renderer, row, column);
                    width = Math.max(comp.getPreferredSize().width + 1, width);
                }
                columnModel.getColumn(column).setPreferredWidth(width);
            }
            DefaultTableCellRenderer tengah = new DefaultTableCellRenderer();
            tengah.setHorizontalAlignment((JLabel.CENTER));
            columnModel.getColumn(0).setCellRenderer(tengah);
            columnModel.getColumn(1).setCellRenderer(tengah);
            columnModel.getColumn(2).setCellRenderer(tengah);
            columnModel.getColumn(3).setCellRenderer(tengah);
            columnModel.getColumn(4).setCellRenderer(tengah);
            columnModel.getColumn(5).setCellRenderer(tengah);
            columnModel.getColumn(6).setCellRenderer(tengah);

            // Menutup koneksi dan statement
            resultSet.close();
            statement.close();
            koneksi.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    }

//    public void createTable() {
//        Object[][] data = {
//                {"TS00001", "Tipe Rumah", "Rumah 9", 1500000000, 9876543210L, "01-JAN-2023"},
//                {"TS00001", "Tipe Industrial", "Mesin Cetak", 500000000, 9876543210L, "05-JAN-2023"}
//        };
//
//        tabelBeli.setModel(new DefaultTableModel(
//                data,
//                new String[]{"ID Transaksi", "Tipe Properti", "Nama Properti", "Harga", "TF ke Rekening", "Tanggal Beli"}
//        ));
//
//        TableColumnModel kolom = tabelBeli.getColumnModel();
//        DefaultTableCellRenderer tengah = new DefaultTableCellRenderer();
//        tengah.setHorizontalAlignment((JLabel.CENTER));
//        kolom.getColumn(0).setCellRenderer(tengah);
//        kolom.getColumn(1).setCellRenderer(tengah);
//        kolom.getColumn(2).setCellRenderer(tengah);
//        kolom.getColumn(3).setCellRenderer(tengah);
//        kolom.getColumn(4).setCellRenderer(tengah);
//        kolom.getColumn(5).setCellRenderer(tengah);
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TabelBeli coba = new TabelBeli();
            coba.setContentPane(coba.TBPanel);
        });
    }
}
