package br.inatel.DAO.ConnectionDAO;

import br.inatel.Model.Autor;

import java.sql.SQLException;
import java.util.ArrayList;

public class AutorDAO extends ConnectionDAO{
    public void testConnection(){
        connectToDb();
    }
    public boolean insertAutor(Autor autor){
        connectToDb();

        boolean sucesso;
        String sql = "INSERT INTO autor (nomeAutor, nacionalidadeAutor) VALUES (?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, autor.getNomeAutor());
            pst.setString(2, autor.getNacionalidadeAutor());
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

    public boolean updateAutor(int idautor, Autor autor){
        connectToDb();

        boolean sucesso;
        String sql = "UPDATE autor SET nomeAutor = ?, nacionalidadeAutor = ? WHERE idautor = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, autor.getNomeAutor());
            pst.setString(2, autor.getNacionalidadeAutor());
            pst.setInt(3, idautor);
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

    public boolean deleteAutor(int id){
        connectToDb();

        boolean sucesso;
        String sql = "DELETE FROM autor WHERE idautor = ?";
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


    public ArrayList<Autor> selectAutor() {
        connectToDb();

        ArrayList<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM autor";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de autores:");
            while (rs.next()) {
                Autor autorAux = new Autor(rs.getInt("idautor"), rs.getString("nomeAutor"), rs.getString("nacionalidadeAutor"));
                System.out.println("ID: " + autorAux.getIdautor() + " Nome: " + autorAux.getNomeAutor() + " Nacionalidade: " + autorAux.getNacionalidadeAutor());
                System.out.println("--------------------");
                autores.add(autorAux);
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
        return autores;
    }
    public Autor selectUnicoAutor(int id){
        connectToDb();
        Autor autorAux = null;
        boolean sucesso;
        String sql = "SELECT * FROM autor WHERE idautor = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            sucesso = true;

            System.out.println("Dados do autor:");
            while (rs.next()) {
                autorAux = new Autor(
                        rs.getInt("idautor"),
                        rs.getString("nomeAutor"),
                        rs.getString("nacionalidadeAutor")
                );
                System.out.println("ID: " + autorAux.getIdautor() +
                        " Nome: " + autorAux.getNomeAutor() +
                        " Nacionalidade: " + autorAux.getNacionalidadeAutor());
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
        return autorAux;
    }





}
