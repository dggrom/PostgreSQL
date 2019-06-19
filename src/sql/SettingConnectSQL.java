package sql;

public class SettingConnectSQL {

    static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/HellowPost";
    static String USER = "postgres";
    static String PASS = "12481248";

    public SettingConnectSQL(String Base_URL, String Base_USER, String Base_PASS){
        DB_URL = Base_URL;
        USER = Base_USER;
        PASS = Base_PASS;
    }

}
