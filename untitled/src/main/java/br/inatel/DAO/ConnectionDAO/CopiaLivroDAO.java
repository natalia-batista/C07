package br.inatel.DAO.ConnectionDAO;


import br.inatel.Model.CopiaLivro;

import java.sql.SQLException;
import java.util.ArrayList;

public class CopiaLivroDAO extends ConnectionDAO{
    public void testConnection(){
        connectToDb();
    }
    public boolean insertCopia(CopiaLivro copia){
        connectToDb();

        boolean sucesso;
        String sql = "INSERT INTO copiaLivro (Livro_idLivro, editoraCopia,edicaoCopia,editora_ideditora,CopiaDisponivel) VALUES (?, ?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, copia.getLivro_idLivro());
            pst.setString(2, copia.getEditoraCopia());
            pst.setInt(3, copia.getEdicaoCopia());
            pst.setInt(4, copia.getEditora_ideditora());
            pst.setString(5, copia.getCopiaDisponivel());
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

    public boolean updateCopia(int idcopia, CopiaLivro copia){
        connectToDb();

        boolean sucesso;
        String sql = "UPDATE copiaLivro SET editoraCopia = ?, edicaoCopia = ?, CopiaDisponivel = ? WHERE idcopia = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, copia.getEditoraCopia());
            pst.setInt(2, copia.getEdicaoCopia());
            pst.setString(3, copia.getCopiaDisponivel());
            pst.setInt(4, idcopia);
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

    public boolean deleteCopia(int id){
        connectToDb();

        boolean sucesso;
        String sql = "DELETE FROM copiaLivro WHERE idcopiaLivro = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
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

    public ArrayList<CopiaLivro> selectCopia() {
        connectToDb();

        ArrayList<CopiaLivro> copias = new ArrayList<>();
        String sql = "SELECT * FROM copiaLivro";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de cópias:");
            while (rs.next()) {
                CopiaLivro copiaAux = new CopiaLivro(
                        rs.getInt("idcopiaLivro"), rs.getInt("Livro_idLivro"),
                        rs.getString("editoraCopia"),rs.getInt("edicaoCopia"),
                        rs.getInt("editora_ideditora"),rs.getString("CopiaDisponivel"));
                System.out.println("ID: " + copiaAux.getIdcopiaLivro() + " Editora: " + copiaAux.getEditoraCopia() +
                        " N° edição: " + copiaAux.getEdicaoCopia() + " Disponibilidade: " + copiaAux.getCopiaDisponivel());
                System.out.println("--------------------");
                copias.add(copiaAux);
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
        return copias;
    }
}
