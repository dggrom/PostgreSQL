package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SettingConnectSQL {

   public String DB_URL ;
   public String USER ;
   public String PASS ;

    public SettingConnectSQL(String Base_URL, String Base_USER, String Base_PASS){
        DB_URL = Base_URL;
        USER = Base_USER;
        PASS = Base_PASS;
    }

    public Connection CreatConnect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(DB_URL,USER,PASS);
        return connection;
    }

}
