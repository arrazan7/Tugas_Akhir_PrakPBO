import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transfer2 extends JFrame{
    public JPanel transaksi2Panel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JButton kembaliButton;
    private JButton sudahButton;

    public Transfer2() {
        setTitle("CEPATLAH TRANSFER !!!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,250);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Membuat instance FormDaftar
                BeliProperti sewa = new BeliProperti();
                // Menetapkan panel FormDaftar sebagai konten utama JFrame
                sewa.setContentPane(sewa.BPPanel);
                // Mengatur ulang tampilan JFrame
                revalidate();
                repaint();
                dispose();
            }
        });

        sudahButton.addActionListener(new ActionListener() {
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

    public static void main(String[] args) {
        Transfer2 coba = new Transfer2();
        coba.setContentPane(coba.transaksi2Panel);
    }
}
