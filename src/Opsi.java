import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Opsi extends JFrame{
    public JPanel opsiPanel;
    private JButton sewaProperti;
    private JButton beliProperti;
    private JButton sewaanAnda;
    private JButton pembelianAnda;
    private JButton logOut;

    public Opsi() {
        setTitle("Pilihan Layanan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,250);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        logOut.addActionListener(new ActionListener() {
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

        sewaProperti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    SewaProperti sewa = new SewaProperti();
                    sewa.setContentPane(sewa.SPPanel);
                });

                // Mengatur ulang tampilan JFrame
                revalidate();
                repaint();
                dispose();
            }
        });

        beliProperti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    BeliProperti beli = new BeliProperti();
                    beli.setContentPane(beli.BPPanel);
                });

                // Mengatur ulang tampilan JFrame
                revalidate();
                repaint();
                dispose();
            }
        });

        sewaanAnda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    TabelSewa anda = new TabelSewa();
                    anda.setContentPane(anda.TSPanel);
                });

                // Mengatur ulang tampilan JFrame
                revalidate();
                repaint();
                dispose();
            }
        });

        pembelianAnda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    TabelBeli anda = new TabelBeli();
                    anda.setContentPane(anda.TBPanel);
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
            Opsi coba = new Opsi();
            coba.setContentPane(coba.opsiPanel);
        });
    }
}
