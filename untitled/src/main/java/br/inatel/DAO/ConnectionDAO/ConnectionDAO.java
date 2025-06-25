package br.inatel.DAO.ConnectionDAO;

import java.sql.*;

public class ConnectionDAO {
    Connection con; //conexão
    PreparedStatement pst; //declaração(query) preparada - código em sql
    Statement st; //declaração(query) - código em sql
    ResultSet rs; //resposta do banco

    String database = "mydb"; //nome do BD
    String user = "root";
    String password = "root";
    String url = "jdbc:mysql://127.0.0.1:3306/" + database;


    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado com sucesso!");
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
        }
    }
}
