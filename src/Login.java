import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame{
    static String username123;
    static Date tanggalSaatini;
    private JTextField textField1;
    private JButton loginButton;
    private JButton signupButton;
    public JPanel loginPanel;
    private JLabel headerLogin;
    private JLabel userLogin;
    private JLabel passLogin;
    private JPasswordField passwordField1;

    public Login() {
        setTitle("Log In");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,160);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                SwingUtilities.invokeLater(() -> {
                    // Membuat instance formIdentity
                    formRegister formIdentity = new formRegister();
                    // Menetapkan panel formIdentity sebagai konten utama JFrame
                    formIdentity.setContentPane(formIdentity.formPanel);
                });

                // Mengatur ulang tampilan JFrame
                revalidate();
                repaint();
                // Menutup frame saat JButton ditekan
                dispose();
                // Atau jika ingin menutup seluruh aplikasi
                // System.exit(0);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String usernameCustomer = textField1.getText();
                String passwordCustomer = passwordField1.getText();

                try {
                    Connection koneksi = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);

                    // Langkah 4: Membuat pernyataan
                    String tgl = "SELECT saat_ini FROM tanggal";
                    PreparedStatement statement = koneksi.prepareStatement(tgl);
                    ResultSet rs = statement.executeQuery();

                    if (rs.next()) {
                        tanggalSaatini = rs.getDate("saat_ini");
                        System.out.println(tanggalSaatini);
                    }

                    rs.close();
                    statement.close();
                    koneksi.close();
                } catch (SQLException a) {
                    a.printStackTrace();
                }


                try {
                    Connection koneksi = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);

                    // Langkah 4: Membuat pernyataan
                    String query = "SELECT username FROM customer WHERE username = ? AND katasandi = ?";
                    PreparedStatement statement2 = koneksi.prepareStatement(query);
                    statement2.setString(1, usernameCustomer);
                    statement2.setString(2, passwordCustomer);

                    // Menjalankan pernyataan SQL dan mendapatkan hasil
                    ResultSet resultSet = statement2.executeQuery();

                    // Memeriksa apakah ada baris hasil yang dikembalikan
                    if (resultSet.next()) {
                        username123 = resultSet.getString("username");

                        resultSet.close();
                        statement2.close();
                        koneksi.close();

                        SwingUtilities.invokeLater(() -> {
                            // Membuat instance Opsi
                            Opsi opsi = new Opsi();
                            // Menetapkan panel opsiPanel sebagai konten utama JFrame
                            opsi.setContentPane(opsi.opsiPanel);
                        });

                        // Mengatur ulang tampilan JFrame
                        revalidate();
                        repaint();
                        // Menutup frame saat JButton ditekan
                        dispose();
                        // Atau jika ingin menutup seluruh aplikasi
                        // System.exit(0);
                    } else {
                        // Jika tidak ada baris hasil, maka username dan katasandi tidak sesuai
                        JOptionPane.showMessageDialog(null, "Log In Salah");
                    }
                }
                catch (SQLException a) {
                    a.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login coba = new Login();
            coba.setContentPane(coba.loginPanel);
        });
    }
}
