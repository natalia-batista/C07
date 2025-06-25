package br.inatel.DAO.ConnectionDAO;



import br.inatel.Model.Emprestimos;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmprestimosDAO extends ConnectionDAO{
    public void testConnection(){
        connectToDb();
    }
    public boolean insertEmprestimo(Emprestimos emprestimo){
        connectToDb();

        boolean sucesso;
        String sql = "INSERT INTO emprestimos ( dataRetirada,dataDevolucao,membro_idmembro,copiaLivro_idcopiaLivro) VALUES ( ?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, emprestimo.getDataRetirada());
            pst.setString(2, emprestimo.getDataDevolucao());
            pst.setInt(3, emprestimo.getMembro_idmembro());
            pst.setInt(4, emprestimo.getCopiaLivro_idcopiaLivro());
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

    public boolean updateEmprestimo(int idemprestimos, Emprestimos emprestimo){
        connectToDb();

        boolean sucesso;
        String sql = "UPDATE emprestimos SET dataRetirada = ?, dataDevolucao = ?, membro_idmembro = ?, copiaLivro_idcopiaLivro = ? WHERE idemprestimos = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, emprestimo.getDataRetirada());
            pst.setString(2,emprestimo.getDataDevolucao());
            pst.setInt(3, emprestimo.getMembro_idmembro());
            pst.setInt(4, emprestimo.getCopiaLivro_idcopiaLivro());
            pst.setInt(4, idemprestimos);
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

    public boolean deleteEmprestimos(int id){
        connectToDb();

        boolean sucesso;
        String sql = "DELETE FROM emprestimos WHERE idemprestimos = ?";
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

    public ArrayList<Emprestimos> selectEmprestimos() {

        connectToDb();

        ArrayList<Emprestimos> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de emprestimos de livros: ");
            while (rs.next()) {
                Emprestimos emprestimoAux = new Emprestimos(
                        rs.getInt("idemprestimos"), rs.getString("dataRetirada"),
                        rs.getString("dataDevolucao"),rs.getInt("membro_idmembro"),
                        rs.getInt("copiaLivro_idcopiaLivro"));
                System.out.println("ID: " + emprestimoAux.getIdemprestimos() + " Data de Retirada: " + emprestimoAux.getDataRetirada() +
                        " Data de Devolução: " + emprestimoAux.getDataDevolucao());
                System.out.println("--------------------");
                emprestimos.add(emprestimoAux);
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
        return emprestimos;
    }
}
