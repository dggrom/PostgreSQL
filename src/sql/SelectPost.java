package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectPost {

    public static void SelectKontragent(Connection Con) throws SQLException {
        Statement Stat = Con.createStatement();
        ResultSet Res = Stat.executeQuery("SELECT * FROM KONTRAGENT;");

        while (Res.next()){
            System.out.println(Res.getString(1));
            System.out.println(Res.getString(2));
        }
    }

}
