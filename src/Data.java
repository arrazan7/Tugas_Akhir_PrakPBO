import java.sql.*;
import java.util.Scanner;

public class Data {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "C##PROYEKPBO";
        String password = "oracledatabase";

        // Langkah 1: Membuat objek Scanner untuk membaca input pengguna
        Scanner user_input = new Scanner(System.in);

        // Langkah 2: Membaca input dari pengguna
        System.out.print("Masukkan Username Anda: ");
        String usernameCustomer = user_input.nextLine();

        System.out.print("Masukkan Password Anda: ");
        String passwordCustomer = user_input.nextLine();

        System.out.print("Masukkan NIK Anda (lima angka): ");
        int NIKCustomer = user_input.nextInt();

        user_input.nextLine(); // Membersihkan baris kosong
        System.out.print("Masukkan Nama Anda: ");
        String namaCustomer = user_input.nextLine();

        System.out.print("Masukkan Jenis Kelamin Anda (L/P): ");
        String genderCustomer = user_input.nextLine();

        System.out.print("Masukkan Alamat Anda: ");
        String alamatCustomer = user_input.nextLine();

        System.out.print("Masukkan Telepon Anda: ");
        String telpCustomer = user_input.nextLine();

        try {
            // Langkah 3: Membuat koneksi
            Connection koneksi = DriverManager.getConnection(url, username, password);

            // Langkah 4: Membuat pernyataan
            String query = "INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = koneksi.prepareStatement(query);
            preparedStatement.setString(1, usernameCustomer);
            preparedStatement.setString(2, passwordCustomer);
            preparedStatement.setInt(3, NIKCustomer);
            preparedStatement.setString(4, namaCustomer);
            preparedStatement.setString(5, genderCustomer);
            preparedStatement.setString(6, alamatCustomer);
            preparedStatement.setString(7, telpCustomer);

            // Langkah 5: Menjalankan pernyataan
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Jumlah baris yang terpengaruh: " + rowsAffected);

            // Langkah 6: Menampilkan isi tabel
            Statement statement = koneksi.createStatement();
            String selectQuery = "SELECT * FROM customer";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            System.out.println("Data dalam tabel customer:");
            while (resultSet.next()) {
                String username_customer = resultSet.getString("username");
                String password_customer = resultSet.getString("katasandi");
                int NIK_customer = resultSet.getInt("nik");
                String nama_customer = resultSet.getString("nama");
                String gender_customer = resultSet.getString("gender");
                String alamat_customer = resultSet.getString("alamat");
                String telp_customer = resultSet.getString("telepon");

                System.out.println(
                        "Username: " + username_customer +
                        " | Password: " + password_customer +
                        " | NIK: " + NIK_customer +
                        " | Nama: " + nama_customer +
                        " | Jenis Kelamin: " + gender_customer +
                        " | Alamat: " + alamat_customer +
                        " | Telepon: " + telp_customer);
            }

            // Langkah 7: Menutup objek-objek
            resultSet.close();
            statement.close();
            preparedStatement.close();
            koneksi.close();
            user_input.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
