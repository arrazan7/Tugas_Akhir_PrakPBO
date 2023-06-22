import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

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
        setTitle("Tabel Sewa Anda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        createTable();


        userLabel.setText(Login.username123);
        // Membuat objek SimpleDateFormat dengan format yang diinginkan
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        // Mengubah objek Date menjadi format teks
        String dateString = dateFormat.format(Login.tanggalSaatini);
        // Mengatur teks pada JLabel
        tglLabel.setText(dateString);

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
        Object[][] data = {
                {"TS00001", "Tipe Rumah", "Rumah 9", 1500000000, 9876543210L, "01-JAN-2023"},
                {"TS00001", "Tipe Industrial", "Mesin Cetak", 500000000, 9876543210L, "05-JAN-2023"}
        };

        tabelBeli.setModel(new DefaultTableModel(
                data,
                new String[]{"ID Transaksi", "Tipe Properti", "Nama Properti", "Harga", "TF ke Rekening", "Tanggal Beli"}
        ));

        TableColumnModel kolom = tabelBeli.getColumnModel();
        DefaultTableCellRenderer tengah = new DefaultTableCellRenderer();
        tengah.setHorizontalAlignment((JLabel.CENTER));
        kolom.getColumn(0).setCellRenderer(tengah);
        kolom.getColumn(1).setCellRenderer(tengah);
        kolom.getColumn(2).setCellRenderer(tengah);
        kolom.getColumn(3).setCellRenderer(tengah);
        kolom.getColumn(4).setCellRenderer(tengah);
        kolom.getColumn(5).setCellRenderer(tengah);
    }

    public static void main(String[] args) {
        TabelBeli coba = new TabelBeli();
        coba.setContentPane(coba.TBPanel);
    }
}
