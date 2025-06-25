package br.inatel.DAO.ConnectionDAO;


import br.inatel.Model.Livro_has_editora;

import java.sql.SQLException;
import java.util.ArrayList;

public class Livro_has_editoraDAO extends ConnectionDAO{
    public void testConnection(){
        connectToDb();
    }
    public boolean insertLivroEditora(Livro_has_editora lve){
        connectToDb();

        boolean sucesso;
        String sql = "INSERT INTO Livro_has_Editora (Livro_idLivro, editora_ideditora) VALUES (?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,lve.getLivro_idLivro());
            pst.setInt(2, lve.getEditora_ideditora());
            pst.execute();
            sucesso = true;

        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }


}
