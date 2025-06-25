package br.inatel.DAO.ConnectionDAO;




import br.inatel.Model.GeneroLiterario;

import java.sql.SQLException;
import java.util.ArrayList;

public class GeneroLiterarioDAO extends ConnectionDAO{
    public void testConnection(){
        connectToDb();
    }
    public boolean insertGenero(GeneroLiterario genero){
        connectToDb();

        boolean sucesso;
        String sql = "INSERT INTO generoLiterario (genero) VALUES ( ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, genero.getGenero());
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

    public boolean updateGenero(int idGenero, GeneroLiterario genero){
        connectToDb();

        boolean sucesso;
        String sql = "UPDATE generoLiterario SET genero = ? WHERE idgeneroLiterario = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, genero.getGenero());
            pst.setInt(2, idGenero);
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

    public boolean deleteGenero(int id){
        connectToDb();

        boolean sucesso;
        String sql = "DELETE FROM generoLiterario WHERE idgeneroLiterario = ?";
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

    public ArrayList<GeneroLiterario> selectGeneroLiterario() {
        connectToDb();

        ArrayList<GeneroLiterario> generos = new ArrayList<>();
        String sql = "SELECT * FROM generoLiterario";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de gêneros literários:");
            while (rs.next()) {
                GeneroLiterario generoAux = new GeneroLiterario(rs.getInt("idgeneroLiterario"), rs.getString("genero"));
                System.out.println("ID: " + generoAux.getIdgeneroLiterario() + " Gênero: " + generoAux.getGenero());
                System.out.println("--------------------");
                generos.add(generoAux);
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
        return generos;
    }
    public GeneroLiterario selectUnicoGenero(int id){
        connectToDb();
        GeneroLiterario generoAux = null;
        boolean sucesso;
        String sql = "SELECT * FROM generoLiterario WHERE idgeneroLiterario = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            sucesso = true;

            System.out.println("Dados do genero literario:");
            while (rs.next()) {
                generoAux = new GeneroLiterario(
                        rs.getInt("idgeneroLiterario"),
                        rs.getString("genero"));

                System.out.println("ID: " + generoAux.getIdgeneroLiterario() +
                                " Gênero: " + generoAux.getGenero());
                System.out.println("--------------------");
            }
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
                rs.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return generoAux;
    }
}
