package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectPost {

    public static void SelectKontragent(Connection Con) throws SQLException {
        Statement Stat = Con.createStatement();
        ResultSet Res = Stat.executeQuery("SELECT id_kont, name_kont FROM public.\"Konteagent\";");

        while (Res.next()){
            System.out.println("�y�������� �������������: " + Res.getString(1) + " ���: " + Res.getString(2));
        }
    }

}
