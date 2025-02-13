package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelp {

    public static Connection getConnection() throws ClassCastException, SQLException, ClassNotFoundException {
    
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String url = "jdbc:mysql://localhost/loja";

        final String user = "root";
        final String password = "willy1999";

        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }
}
