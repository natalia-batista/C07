package br.inatel.DAO.ConnectionDAO;


import br.inatel.Model.Resenha;

import java.sql.SQLException;
import java.util.ArrayList;

public class ResenhaDAO extends ConnectionDAO{
    public void testConnection(){
        connectToDb();
    }
    public boolean insertResenha(Resenha resenha){
        connectToDb();

        boolean sucesso;
        String sql = "INSERT INTO resenha (nota, comentario,membro_idmembro,Livro_idLivro) VALUES ( ?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, resenha.getNota());
            pst.setString(2, resenha.getComentario());
            pst.setInt(3, resenha.getMembro_idmembro());
            pst.setInt(4, resenha.getLivro_idLivro());
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


    public ArrayList<Resenha> selectResenhas() {

        connectToDb();

        ArrayList<Resenha> resenhas = new ArrayList<>();
        String sql = "SELECT * FROM resenha";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de resenhas de livros: ");
            while (rs.next()) {
                Resenha resenhaAux = new Resenha(
                        rs.getInt("nota"), rs.getString("comentario"),
                        rs.getInt("membro_idmembro"),rs.getInt("Livro_idLivro"));
                System.out.println("Nota: " + resenhaAux.getNota() + " Comentário: " + resenhaAux.getComentario());
                System.out.println("--------------------");
                resenhas.add(resenhaAux);
            }

        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
        } finally {
            try {
                con.close();
                st.close();
                rs.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return resenhas;
    }
}
