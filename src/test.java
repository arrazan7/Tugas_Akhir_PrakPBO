
import java.sql.*;

public class test {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "C##PROYEKPBO";
        String password = "oracledatabase";

        try {
            // Langkah 3: Membuat koneksi
            Connection koneksi = DriverManager.getConnection(url, username, password);
            System.out.println(koneksi);
//
            // Langkah 4: Membuat pernyataan
            CallableStatement callableStatement = koneksi.prepareCall("{call menyewa('fayy', 'Tipe Komersial', 'Ruko1', '03-01-2023', '16-01-2023', 1122334455)}");
            callableStatement.execute();
//            PreparedStatement preparedStatement = koneksi.prepareStatement(query);
//
//            preparedStatement.executeUpdate();
//            // Langkah 5: Menutup objek-objek
//            preparedStatement.close();
//            koneksi.close();
        }
        catch (SQLException a) {
            a.printStackTrace();
        }
    }
}