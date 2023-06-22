import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String[][] coba1 = {{"Nama", "Provinsi", "Properti"}, {"AA", "Bandung", "Gedung"}, {"BB", "Yogyakarta", "Mobil"}, {"CC", "Surabaya", "Laptop"}};

        // Menghitung panjang maksimum setiap kolom
        int[] columnWidths = new int[coba1[0].length];
        for (String[] row : coba1) {
            for (int i = 0; i < row.length; i++) {
                if (row[i].length() > columnWidths[i]) {
                    columnWidths[i] = row[i].length();
                }
            }
        }

        // Menampilkan tabel
        for (String[] row : coba1) {
            for (int i = 0; i < row.length; i++) {
                System.out.print("| " + padString(row[i], columnWidths[i]) + " ");
            }
            System.out.println("|");

            // Menampilkan baris pemisah setelah header
            if (row == coba1[0]) {
                for (int i = 0; i < row.length; i++) {
                    System.out.print("+" + repeatChar('-', columnWidths[i] + 2));
                }
                System.out.println("+");
            }
        }
    }

    // Metode untuk memasukkan spasi tambahan pada nilai kolom
    private static String padString(String input, int length) {
        StringBuilder paddedString = new StringBuilder(input);
        while (paddedString.length() < length) {
            paddedString.append(" ");
        }
        return paddedString.toString();
    }

    // Metode untuk mengulangi karakter tertentu sebanyak n kali
    private static String repeatChar(char c, int n) {
        StringBuilder repeatedChars = new StringBuilder();
        for (int i = 0; i < n; i++) {
            repeatedChars.append(c);
        }
        return repeatedChars.toString();
    }
}

