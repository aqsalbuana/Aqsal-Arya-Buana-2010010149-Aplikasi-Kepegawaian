package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Koneksi {
    
    private final String url = "jdbc:mysql://localhost:3306/sistem_kepegawaian";
    private final String user = "root";
    private final String pass = "";
    
    public Connection getKoneksi(){
        Connection koneksi;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection(url, user, pass);
            System.out.println("koneksi berhasil");
            return koneksi;
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("koneksi gagal"+ex);
            return koneksi = null;
        }
    }
    
}
