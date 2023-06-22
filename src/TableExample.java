import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TableExample extends JFrame {
    private JTable table;

    public TableExample() {
        setTitle("Tabel Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat objek JTable
        table = new JTable();

        // Mendapatkan data dari database dan mengisi tabel
        fetchDataAndFillTable();

        // Menambahkan tabel ke JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Mengatur layout frame
        setLayout(new BorderLayout());

        // Menambahkan JScrollPane ke frame
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    // Method untuk mendapatkan data dari database dan mengisi tabel
    private void fetchDataAndFillTable() {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "C##PROYEKPBO";
        String password = "oracledatabase";

        try {
            // Membuat koneksi ke database
            Connection connection = DriverManager.getConnection(url, username, password);

            // Membuat statement SQL
            Statement statement = connection.createStatement();

            // Eksekusi query untuk mendapatkan data dari tabel
            String query = "SELECT * FROM customer";
            ResultSet resultSet = statement.executeQuery(query);

            // Membuat model tabel
            DefaultTableModel tableModel = new DefaultTableModel();

            // Mendapatkan metadata kolom dari result set
            ResultSetMetaData metaData = resultSet.getMetaData();

            // Mendapatkan jumlah kolom
            int columnCount = metaData.getColumnCount();

            // Menambahkan kolom ke model tabel
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                tableModel.addColumn(metaData.getColumnName(columnIndex));
            }

            // Menambahkan baris-baris data ke model tabel
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
                }
                tableModel.addRow(rowData);
            }

            // Mengatur model tabel
            table.setModel(tableModel);

            // Menutup statement, result set, dan koneksi
            statement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TableExample();
        });
    }
}

//import javax.swing.*;
//        import java.awt.event.ActionEvent;
//        import java.awt.event.ActionListener;
//        import java.sql.*;
//
//public class FormRegister extends JFrame{
//    public JPanel formPanel;
//    private JLabel headerForm;
//    private JLabel userLabel;
//    private JLabel passLabel;
//    private JLabel nikLabel;
//    private JLabel namaLabel;
//    private JLabel genderLabel;
//    private JLabel alamatLabel;
//    private JLabel teleponLabel;
//    private JTextField textField1;
//    private JTextField textField2;
//    private JTextField textField3;
//    private JTextField textField4;
//    private JTextField textField5;
//    private JTextField textField6;
//    private JTextField textField7;
//    private JButton daftarButton;
//    private JButton kembaliButton;
//    private JRadioButton lakiLakiRadioButton;
//    private JRadioButton perempuanRadioButton;
//
//    public FormRegister() {
//        setTitle("Formulir Identitas");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(500,300);
//        setLocationRelativeTo(null);
//        setResizable(false);
//        setVisible(true);
//
//        kembaliButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Membuat instance FormDaftar
//                Login login = new Login();
//                // Menetapkan panel FormDaftar sebagai konten utama JFrame
//                login.setContentPane(login.loginPanel);
//                // Mengatur ulang tampilan JFrame
//                revalidate();
//                repaint();
//                dispose();
//            }
//        });
//
//        daftarButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String url = "jdbc:oracle:thin:@localhost:1521:XE";
//                String username = "C##PROYEKPBO";
//                String password = "oracledatabase";
//
//
//                String usernameCustomer = textField1.getText();
//                String passwordCustomer = textField2.getText();
//                int NIKCustomer = Integer.parseInt(textField3.getText());
//                String namaCustomer = textField4.getText();
//                String genderCustomer = textField5.getText();
//                String alamatCustomer = textField6.getText();
//                String telpCustomer = textField7.getText();
//
//                try {
//                    // Langkah 3: Membuat koneksi
//                    Connection koneksi = DriverManager.getConnection(url, username, password);
//
//                    // Langkah 4: Membuat pernyataan
//                    String query = "INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?)";
//                    PreparedStatement preparedStatement = koneksi.prepareStatement(query);
//                    preparedStatement.setString(1, usernameCustomer);
//                    preparedStatement.setString(2, passwordCustomer);
//                    preparedStatement.setInt(3, NIKCustomer);
//                    preparedStatement.setString(4, namaCustomer);
//                    preparedStatement.setString(5, genderCustomer);
//                    preparedStatement.setString(6, alamatCustomer);
//                    preparedStatement.setString(7, telpCustomer);
//
//                    // Langkah 5: Menutup objek-objek
//                    preparedStatement.close();
//                    koneksi.close();
//
//                    // Membuat instance FormIdentity
//                    Login login = new Login();
//                    // Menetapkan panel FormIdentity sebagai konten utama JFrame
//                    login.setContentPane(login.loginPanel);
//                    // Mengatur ulang tampilan JFrame
//                    revalidate();
//                    repaint();
//                    dispose();
//                }
//                catch (SQLException a) {
//                    a.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public static void main(String[] args) {
//        FormRegister coba = new FormRegister();
//        coba.setContentPane(coba.formPanel);
//    }
//}

