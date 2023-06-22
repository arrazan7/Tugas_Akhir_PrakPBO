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
                // Membuat instance FormIdentity
                formRegister formIdentity = new formRegister();
                // Menetapkan panel FormIdentity sebagai konten utama JFrame
                formIdentity.setContentPane(formIdentity.formPanel);
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
                    PreparedStatement preparedStatement2 = koneksi.prepareStatement(tgl);
                    ResultSet resultSet2 = preparedStatement2.executeQuery();

                    if (resultSet2.next()) {
                        tanggalSaatini = resultSet2.getDate("saat_ini");
                    }
                } catch (SQLException a) {
                    a.printStackTrace();
                }


                try {
                    Connection koneksi = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);

                    // Langkah 4: Membuat pernyataan
                    String query = "SELECT username FROM customer WHERE username = ? AND katasandi = ?";
                    String tgl = "SELECT saat_ini FROM tanggal";
                    PreparedStatement preparedStatement = koneksi.prepareStatement(query);
//                    PreparedStatement preparedStatement2 = koneksi.prepareStatement(tgl);
                    preparedStatement.setString(1, usernameCustomer);
                    preparedStatement.setString(2, passwordCustomer);

                    // Menjalankan pernyataan SQL dan mendapatkan hasil
                    ResultSet resultSet = preparedStatement.executeQuery();
//                    ResultSet resultSet2 = preparedStatement2.executeQuery();

//                    tanggalSaatini = String.valueOf(resultSet2.getDate("saat_ini"));

                    // Memeriksa apakah ada baris hasil yang dikembalikan
                    if (resultSet.next()) {
                        username123 = resultSet.getString("username");

                        preparedStatement.close();
                        koneksi.close();
                        resultSet.close();

                        // Membuat instance Opsi
                        Opsi opsi = new Opsi();
                        // Menetapkan panel opsiPanel sebagai konten utama JFrame
                        opsi.setContentPane(opsi.opsiPanel);
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
        Login coba = new Login();
        coba.setContentPane(coba.loginPanel);
    }
}
