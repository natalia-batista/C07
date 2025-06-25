package br.inatel.DAO.ConnectionDAO;



import br.inatel.Model.Membro;

import java.sql.SQLException;
import java.util.ArrayList;

public class MembroDAO extends ConnectionDAO{
    public void testConnection(){
        connectToDb();
    }
    public boolean insertMembro(Membro membro){
        connectToDb();

        boolean sucesso;
        String sql = "INSERT INTO membro (nomeMembro, enderecoMembro,telefoneMembro,emailMembro,status) VALUES (?, ?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, membro.getNomeMembro());
            pst.setString(2, membro.getEnderecoMembro());
            pst.setString(3, membro.getTelefoneMembro());
            pst.setString(4, membro.getEmailMembro());
            pst.setString(5, membro.getStatus());
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

    public boolean updateMembro(int id, Membro membro){
        connectToDb();

        boolean sucesso;
        String sql = "UPDATE membro SET nomeMembro = ?, enderecoMembro = ?, telefoneMembro = ?,emailMembro = ?, status = ? WHERE id = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, membro.getNomeMembro());
            pst.setString(2, membro.getEnderecoMembro());
            pst.setString(3, membro.getTelefoneMembro());
            pst.setString(4, membro.getEmailMembro());
            pst.setString(5, membro.getStatus());
            pst.setInt(6, id);
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

    public boolean deleteMembro(int id){
        connectToDb();

        boolean sucesso;
        String sql = "DELETE FROM membro WHERE idmembro = ?";
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

    public ArrayList<Membro> selectMembro() {
        connectToDb();

        ArrayList<Membro> membros = new ArrayList<>();
        String sql = "SELECT * FROM membro";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de membros: ");
            while (rs.next()) {
                Membro membroAux = new Membro(
                        rs.getInt("idmembro"), rs.getString("nomeMembro"),
                        rs.getString("enderecoMembro"),rs.getString("telefoneMembro"),
                        rs.getString("emailMembro"),rs.getString("status"));
                System.out.println("ID: " + membroAux.getIdmembro() + " Nome: " + membroAux.getNomeMembro() +
                        " Endereco: " + membroAux.getEnderecoMembro() + " Telefone: " + membroAux.getTelefoneMembro() +
                        " Email: " + membroAux.getEmailMembro() + " Status: " + membroAux.getStatus());
                System.out.println("--------------------");
                membros.add(membroAux);
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
        return membros;
    }
    public Membro selectUnicoMembro(int id){
        connectToDb();
        Membro membroAux = null;
        boolean sucesso;
        String sql = "SELECT * FROM membro WHERE idmembro = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            sucesso = true;

            System.out.println("Dados do membro:");
            while (rs.next()) {
                membroAux = new Membro(
                        rs.getInt("idmembro"), rs.getString("nomeMembro"),
                        rs.getString("enderecoMembro"),rs.getString("telefoneMembro"),
                        rs.getString("emailMembro"),rs.getString("status"));
                System.out.println("ID: " + membroAux.getIdmembro() + " Nome: " + membroAux.getNomeMembro() +
                        " Endereco: " + membroAux.getEnderecoMembro() + " Telefone: " + membroAux.getTelefoneMembro() +
                        " Email: " + membroAux.getEmailMembro() + " Status: " + membroAux.getStatus());
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
        return membroAux;
    }
}
