package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectPost {

    public ResultSet SelectKontragent(Connection Con) throws SQLException {
        Statement Stat = Con.createStatement();
        ResultSet Res = Stat.executeQuery("SELECT id_kont, name_kont, deleted_kont FROM public.\"Kontragent\";");

        return Res;
    }

    public Boolean UpdateCreatTable(Connection Con, String textSQL) throws SQLException {
        Statement Stat = Con.createStatement();
        boolean Res = Stat.execute(textSQL);

        return Res;
    }

}
