package br.inatel.DAO.ConnectionDAO;



import br.inatel.Model.Editora;


import java.sql.SQLException;
import java.util.ArrayList;

public class EditoraDAO extends ConnectionDAO{
    public void testConnection(){
        connectToDb();
    }
    public boolean insertEditora(Editora editora){
        connectToDb();

        boolean sucesso;
        String sql = "INSERT INTO editora (nomeEditora,telefoneEditora,emailEditora) VALUES ( ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, editora.getNomeEditora());
            pst.setString(2, editora.getTelefoneEditora());
            pst.setString(3, editora.getEmailEditora());
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

    public boolean updateEditora(int ideditora, Editora editora){
        connectToDb();

        boolean sucesso;
        String sql = "UPDATE editora SET nomeEditora = ?, telefoneEditora = ?, emailEditora = ? WHERE ideditora = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, editora.getNomeEditora());
            pst.setString(2,editora.getTelefoneEditora());
            pst.setString(3, editora.getEmailEditora());
            pst.setInt(4, ideditora);
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

    public boolean deleteEditora(int id){
        connectToDb();

        boolean sucesso;
        String sql = "DELETE FROM editora WHERE ideditora = ?";
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

    public ArrayList<Editora> selectEditora() {

        connectToDb();

        ArrayList<Editora> editoras = new ArrayList<>();
        String sql = "SELECT * FROM editora";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de editoras: ");
            while (rs.next()) {
                Editora editoraAux = new Editora(
                        rs.getInt("ideditora"), rs.getString("nomeEditora"),
                        rs.getString("telefoneEditora"),rs.getString("emailEditora"));
                System.out.println("ID: " + editoraAux.getIdeditora() + " Editora: " + editoraAux.getNomeEditora() +
                        " Telefone: " + editoraAux.getTelefoneEditora() + " Email: " + editoraAux.getEmailEditora());
                System.out.println("--------------------");
                editoras.add(editoraAux);
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
        return editoras;
    }
    public Editora selectUnicaEditora(int id){
        connectToDb();
        Editora editoraAux = null;
        boolean sucesso;
        String sql = "SELECT * FROM editora WHERE ideditora = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            sucesso = true;

            System.out.println("Dados da editora:");
            while (rs.next()) {
                editoraAux = new Editora(
                        rs.getInt("ideditora"), rs.getString("nomeEditora"),
                        rs.getString("telefoneEditora"),rs.getString("emailEditora"));
                System.out.println("ID: " + editoraAux.getIdeditora() + " Editora: " + editoraAux.getNomeEditora() +
                        " Telefone: " + editoraAux.getTelefoneEditora() + " Email: " + editoraAux.getEmailEditora());
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
        return editoraAux;
    }
}
