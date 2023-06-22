//import javax.swing.*;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableColumnModel;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.*;
//import java.text.SimpleDateFormat;
//import java.util.Vector;
//
//public class TabelSewa extends JFrame{
//    private JTable tabelSewa;
//    private JButton kembaliButton;
//    private JButton berhentiMenyewaButton;
//    public JPanel TSPanel;
//    private JLabel userName ;
//    private JLabel currentDate;
//    private JPanel panel1;
//    private JPanel panel2;
//    private JPanel panel3;
//    private JScrollPane scrollTabel;
//    private JLabel userLabel;
//    private JLabel tglLabel;
//
//    public TabelSewa() {
//        setTitle("Tabel Sewa Anda");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(900, 250);
//        setLocationRelativeTo(null);
//        setResizable(false);
//        setVisible(true);
//        userLabel.setText(Login.username123);
//
//        // Membuat objek SimpleDateFormat dengan format yang diinginkan
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        // Mengubah objek Date menjadi format teks
//        String dateString = dateFormat.format(Login.tanggalSaatini);
//        // Mengatur teks pada JLabel
//        tglLabel.setText(dateString);
//
//        createTable();
//
//        String url = "jdbc:oracle:thin:@localhost:1521:XE";
//        String username = "C##PROYEKPBO";
//        String password = "oracledatabase";
//
//        kembaliButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Membuat instance FormDaftar
//                Opsi pilihan = new Opsi();
//                // Menetapkan panel FormDaftar sebagai konten utama JFrame
//                pilihan.setContentPane(pilihan.opsiPanel);
//                // Mengatur ulang tampilan JFrame
//                revalidate();
//                repaint();
//                dispose();
//            }
//        });
//    }
//
//    public void createTable() {
//        Object[][] data = {
//                {"TS00001", "Tipe Rumah", "Rumah 6", "03-JAN-23", "20-JAN-23", 1122334455, "BERHENTI", 0, 0},
//                {"TS00002", "Tipe Industrial", "Mesin Bor", "03-JAN-23", "16-JAN-23", 1122334455, "BERHENTI", 0, 0},
//        };
//
//        tabelSewa.setModel(new DefaultTableModel(
//                data,
//                new String[]{"ID Transaksi", "Tipe Properti", "Nama Properti", "Tanggal Mulai", "Tanggal Selesai", "TF ke Rekening", "Status", "Terlambat", "Denda"}
//        ));
//
//        TableColumnModel kolom = tabelSewa.getColumnModel();
//        DefaultTableCellRenderer tengah = new DefaultTableCellRenderer();
//        tengah.setHorizontalAlignment((JLabel.CENTER));
//        kolom.getColumn(0).setCellRenderer(tengah);
//        kolom.getColumn(1).setCellRenderer(tengah);
//        kolom.getColumn(2).setCellRenderer(tengah);
//        kolom.getColumn(3).setCellRenderer(tengah);
//        kolom.getColumn(4).setCellRenderer(tengah);
//        kolom.getColumn(5).setCellRenderer(tengah);
//        kolom.getColumn(6).setCellRenderer(tengah);
//        kolom.getColumn(7).setCellRenderer(tengah);
//        kolom.getColumn(8).setCellRenderer(tengah);
//
//        String url = "jdbc:oracle:thin:@localhost:1521:XE";
//        String username = "C##PROYEKPBO";
//        String password = "oracledatabase";
//
////        try {
////            Connection koneksi = DriverManager.getConnection(url, username, password);
////
////            // Membuat pernyataan SQL untuk mendapatkan data dari tabel
////            String query = "SELECT id_transaksi, tipe_properti, nama_properti, mulai_sewa, selesai_sewa, no_rekening, status, terlambat, total_denda FROM transaksi_sewa";
////            PreparedStatement pst = koneksi.prepareStatement(query);
////            ResultSet resultSet = pst.executeQuery(query);
////            ResultSetMetaData data = resultSet.getMetaData();
////            int q = data.getColumnCount();
////            DefaultTableModel rekaman = (DefaultTableModel)tabelSewa.getModel();
////            rekaman.setRowCount(0);
////
////            while (resultSet.next()) {
////                Vector kolom = new Vector<>();
////                for (int i = 1; i <= q; i++){
////                    kolom.add(resultSet.getString("id_transaksi"));
////                    kolom.add(resultSet.getString("tipe_properti"));
////                    kolom.add(resultSet.getString("nama_properti"));
////                    kolom.add(resultSet.getDate("mulai_sewa"));
////                    kolom.add(resultSet.getDate("selesai_sewa"));
////                    kolom.add(resultSet.getLong("no_rekening"));
////                    kolom.add(resultSet.getString("status"));
////                    kolom.add(resultSet.getInt("terlambat"));
////                    kolom.add(resultSet.getInt("total_denda"));
////                }
////                rekaman.addRow(kolom);
////            }
////        }
////        catch (SQLException e) {
//////            e.printStackTrace();
////        }
//
////        try {
////            // Mengatur koneksi ke database
////            Connection connection = DriverManager.getConnection(url, username, password);
////            Statement statement = connection.createStatement();
////
////            // Membuat pernyataan SQL untuk mendapatkan data dari tabel
////            String query = "SELECT id_transaksi, tipe_properti, nama_properti, mulai_sewa, selesai_sewa, no_rekening, status, terlambat, total_denda FROM transaksi_sewa";
////
////            ResultSet resultSet = statement.executeQuery(query);
////
////            while (resultSet.next()) {
////                String id = String.valueOf(resultSet.getString("id_transaksi"));
////                String tipe = resultSet.getString("tipe_properti");
////                String nama = resultSet.getString("nama_properti");
////                String mulai = String.valueOf(resultSet.getDate("mulai_sewa"));
////                String selesai = String.valueOf(resultSet.getDate("selesai_sewa"));
////                String rek = String.valueOf(resultSet.getInt("no_rekening"));
////                String status = resultSet.getString("status");
////                String telat = String.valueOf(resultSet.getInt("terlambat"));
////                String denda = String.valueOf(resultSet.getInt("total_denda"));
////
////                String data[] = {"ID Transaksi", "Tipe Properti", "Nama Properti", "Tanggal Mulai", "Tanggal Selesai", "TF ke Rekening", "Status", "Terlambat", "Denda"};
////                DefaultTableModel model1 = (DefaultTableModel)tabelSewa.getModel();
////                model1.addRow(data);
////            }
//
////            // Menghitung jumlah baris dalam ResultSet
////            int rowCount = 0;
////            if (resultSet.last()) {
////                rowCount = resultSet.getRow();
////                resultSet.beforeFirst();
////            }
////
////            // Menghitung jumlah kolom dalam ResultSet
////            ResultSetMetaData metaData = resultSet.getMetaData();
////            int columnCount = metaData.getColumnCount();
////
////            // Membuat array untuk menyimpan data
////            Object[][] data = new Object[rowCount][columnCount];
////
////            // Mengisi data dari ResultSet ke array data
////            int row = 0;
////            while (resultSet.next()) {
////                for (int col = 0; col < columnCount; col++) {
////                    data[row][col] = resultSet.getObject(col + 1);
////                }
////                row++;
////            }
////
////            // Mengatur model tabel dengan data yang diambil
////            tabelSewa.setModel(new DefaultTableModel(
////                    data,
////                    new String[]{"ID Transaksi", "Tipe Properti", "Nama Properti", "Tanggal Mulai", "Tanggal Selesai", "TF ke Rekening", "Status", "Terlambat", "Denda"}
////            ));
////
////            // Menutup koneksi dan statement setelah selesai
////            resultSet.close();
////            statement.close();
////            connection.close();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//    }
//
//    public static void main(String[] args) {
//        TabelSewa coba = new TabelSewa();
//        coba.setContentPane(coba.TSPanel);
//    }
//}
//
//// SELECT id_transaksi, tipe_properti, nama_properti, mulai_sewa, selesai_sewa, no_rekening, status, terlambat, total_denda FROM transaksi_sewa


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

public class TabelSewa extends JFrame {
    private JTable tabelSewa;
    private JButton kembaliButton;
    private JButton berhentiMenyewaButton;
    public JPanel TSPanel;
    private JLabel userName;
    private JLabel currentDate;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JScrollPane scrollTabel;
    private JLabel userLabel;
    private JLabel tglLabel;

    public TabelSewa() {
        setTitle("Tabel Sewa Anda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        userLabel.setText(Login.username123);

        // Membuat objek SimpleDateFormat dengan format yang diinginkan
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        // Mengubah objek Date menjadi format teks
        String dateString = dateFormat.format(Login.tanggalSaatini);
        // Mengatur teks pada JLabel
        tglLabel.setText(dateString);

        createTable();

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
    }

    public void createTable() {

        try (Connection conn = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);
             Statement statement = conn.createStatement()) {
            String query = "SELECT id_transaksi, tipe_properti, nama_properti, mulai_sewa, selesai_sewa, no_rekening, status, terlambat, total_denda FROM transaksi_sewa";
            ResultSet resultSet = statement.executeQuery(query);

            // Get the number of rows in the ResultSet
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            // Get the number of columns in the ResultSet
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Create a 2D array to hold the table data
            Object[][] data = new Object[rowCount][columnCount];
            int row = 0;
            while (resultSet.next()) {
                for (int col = 0; col < columnCount; col++) {
                    data[row][col] = resultSet.getObject(col + 1);
                }
                row++;
            }

            // Create a DefaultTableModel with the retrieved data and column names
            DefaultTableModel tableModel = new DefaultTableModel(data, getColumnNames(metaData));

            // Set the table model to the JTable
            tabelSewa.setModel(tableModel);

            // Center align the cells in each column
            TableColumnModel columnModel = tabelSewa.getColumnModel();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < columnCount; i++) {
                columnModel.getColumn(i).setCellRenderer(centerRenderer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String[] getColumnNames(ResultSetMetaData metaData) throws SQLException {
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = metaData.getColumnName(i + 1);
        }
        return columnNames;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TabelSewa().setVisible(true);
        });
    }
}

