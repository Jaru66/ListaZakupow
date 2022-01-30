//package database;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        Menu.menu();
        //MojaLista.getConnection();
        //MojeProdukty.getConnection();
    }
}
