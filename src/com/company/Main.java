package com.company;

import java.sql.Connection;
import java.sql.SQLException;

import sql.Connect;
import sql.SelectPost;

public class Main {

    static Connection ConnectSQL = null;

    public static void main(String[] args) throws SQLException {
        Connect NewConnect = new Connect();
        ConnectSQL = NewConnect.ConnectPostgre();

        if (ConnectSQL == null){
            System.out.println("null");
            return;
        }else{
            System.out.println("not null");
            SelectPost.SelectKontragent(ConnectSQL);
        }
    }

}
