import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class formRegister extends JFrame{
    public JPanel formPanel;
    private JLabel headLabel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JLabel userLab;
    private JLabel passLab;
    private JLabel nikLab;
    private JLabel namaLab;
    private JLabel genderLab;
    private JLabel alamatLab;
    private JLabel telpLabel;
    private JButton kembaliButton;
    private JButton daftarButton;
    private JRadioButton lakiRB;
    private JRadioButton perempuanRB;
    private ButtonGroup genderGB;


    public formRegister() {
        setTitle("Formulir Pendaftaran");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    Login login = new Login();
                    login.setContentPane(login.loginPanel);
                });

                // Mengatur ulang tampilan JFrame
                revalidate();
                repaint();
                dispose();
            }
        });

        daftarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usernameCustomer = textField1.getText();
                String passwordCustomer = textField2.getText();
                int NIKCustomer = Integer.parseInt(textField3.getText());
                String namaCustomer = textField4.getText();
                String genderCustomer = "gagal";
                String alamatCustomer = textField5.getText();
                String telpCustomer = textField6.getText();


                if (lakiRB.isSelected()) {
                    genderCustomer = "L";
                } 
                else if (perempuanRB.isSelected()) {
                    genderCustomer = "P";
                }

                try {
                    // Langkah 3: Membuat koneksi
                    Connection koneksi = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);

                    // Langkah 4: Membuat pernyataan
                    String query = "INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = koneksi.prepareStatement(query);
                    statement.setString(1, usernameCustomer);
                    statement.setString(2, passwordCustomer);
                    statement.setInt(3, NIKCustomer);
                    statement.setString(4, namaCustomer);
                    statement.setString(5, genderCustomer);
                    statement.setString(6, alamatCustomer);
                    statement.setString(7, telpCustomer);

                    statement.executeUpdate();
                    // Langkah 5: Menutup objek-objek
                    statement.close();
                    koneksi.close();

                    SwingUtilities.invokeLater(() -> {
                        Login login = new Login();
                        login.setContentPane(login.loginPanel);
                    });

                    // Mengatur ulang tampilan JFrame
                    revalidate();
                    repaint();
                    dispose();
                }
                catch (SQLException a) {
                    a.printStackTrace();
                }
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            formRegister coba = new formRegister();
            coba.setContentPane(coba.formPanel);
        });
    }
}
