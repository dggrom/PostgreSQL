package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectPost {

    public ResultSet SelectInfoBase(Connection Con, String SQLtext) throws SQLException {
        Statement Stat = Con.createStatement();
        ResultSet Res = Stat.executeQuery(SQLtext);

        return Res;
    }

    public Boolean UpdateCreatTable(Connection Con, String textSQL) throws SQLException {
        Statement Stat = Con.createStatement();
        boolean Res = Stat.execute(textSQL);

        return Res;
    }

}
