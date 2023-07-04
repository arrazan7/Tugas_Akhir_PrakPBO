
import java.sql.*;

public class test {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "C##PROYEKPBO";
        String password = "oracledatabase";
        String tanggalSaatini;

        try {
            Connection koneksi = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);

            // Langkah 4: Membuat pernyataan
            String tgl = "SELECT saat_ini FROM tanggal";
            PreparedStatement statement = koneksi.prepareStatement(tgl);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                tanggalSaatini = String.valueOf(rs.getDate("saat_ini"));
                System.out.println(tanggalSaatini);
            }

            rs.close();
            statement.close();
            koneksi.close();
        } catch (SQLException a) {
            a.printStackTrace();
        }
    }
}