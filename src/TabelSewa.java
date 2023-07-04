import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class TabelSewa extends JFrame{
    private JTable tabelSewa;
    private JButton kembaliButton;
    private JButton berhentiMenyewaButton;
    public JPanel TSPanel;
    private JLabel userName ;
    private JLabel currentDate;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JScrollPane scrollTabel;
    private JLabel userLabel;
    private JLabel tglLabel;
    private String selectedIdTransaksi;
    private String selectedTipeProperti;
    private String selectedNamaProperti;

    public TabelSewa() {
        setTitle("Tabel Sewa Anda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 250);
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

        try {
            // Menghubungkan ke database
            Connection koneksi = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);

            String query = "SELECT id_transaksi, tipe_properti, nama_properti, mulai_sewa, selesai_sewa, no_rekening, status, terlambat, total_denda FROM transaksi_sewa WHERE username = ?";
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
            tabelSewa.setModel(tableModel);

            // Menyesuaikan lebar kolom secara otomatis
            TableColumnModel columnModel = tabelSewa.getColumnModel();
            for (int column = 0; column < columnCount; column++) {
                int width = 15; // Lebar default
                for (int row = 0; row < data.size(); row++) {
                    TableCellRenderer renderer = tabelSewa.getCellRenderer(row, column);
                    Component comp = tabelSewa.prepareRenderer(renderer, row, column);
                    width = Math.max(comp.getPreferredSize().width + 1, width);
                }
                columnModel.getColumn(column).setPreferredWidth(width);
            }

            // Mengatur agar data berada di tengah sel tabel (Horizontal Alignment Center)
            DefaultTableCellRenderer tengah = new DefaultTableCellRenderer();
            tengah.setHorizontalAlignment((JLabel.CENTER));
            columnModel.getColumn(0).setCellRenderer(tengah);
            columnModel.getColumn(1).setCellRenderer(tengah);
            columnModel.getColumn(2).setCellRenderer(tengah);
            columnModel.getColumn(3).setCellRenderer(tengah);
            columnModel.getColumn(4).setCellRenderer(tengah);
            columnModel.getColumn(5).setCellRenderer(tengah);
            columnModel.getColumn(6).setCellRenderer(tengah);
            columnModel.getColumn(7).setCellRenderer(tengah);
            columnModel.getColumn(8).setCellRenderer(tengah);

            // Menutup koneksi dan statement
            resultSet.close();
            statement.close();
            koneksi.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tabelSewa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tabelSewa.getSelectedRow();
                selectedIdTransaksi = tabelSewa.getValueAt(row, 0).toString();
                selectedTipeProperti = tabelSewa.getValueAt(row, 1).toString();
                selectedNamaProperti = tabelSewa.getValueAt(row, 2).toString();
            }
        });

        berhentiMenyewaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Gunakan nilai selectedIdTransaksi, selectedTipeProperti, dan selectedNamaProperti di sini
                if (selectedIdTransaksi != null && selectedTipeProperti != null && selectedNamaProperti != null) {
                    try {
                        Connection koneksi = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);

                        String query = "{call hitung_denda(?, ?, ?)}";
                        CallableStatement statement = koneksi.prepareCall(query);

                        // Mengatur nilai parameter
                        statement.setString(1, selectedIdTransaksi);
                        statement.setString(2, selectedTipeProperti);
                        statement.setString(3, selectedNamaProperti);

                        statement.execute();
                        statement.close();
                        koneksi.close();
                    } catch (SQLException a) {
                        a.printStackTrace();
                    }

                    SwingUtilities.invokeLater(() -> {
                        TabelSewa tabel = new TabelSewa();
                        tabel.setContentPane(tabel.TSPanel);
                    });

                    // Mengatur ulang tampilan JFrame
                    revalidate();
                    repaint();
                    dispose();
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TabelSewa coba = new TabelSewa();
            coba.setContentPane(coba.TSPanel);
        });
    }
}

