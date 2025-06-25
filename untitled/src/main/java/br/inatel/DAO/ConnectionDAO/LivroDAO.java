package br.inatel.DAO.ConnectionDAO;


import br.inatel.Model.Livro;

import java.sql.SQLException;
import java.util.ArrayList;

public class LivroDAO extends ConnectionDAO{
    public void testConnection(){
        connectToDb();
    }
    public boolean insertLivro(Livro livro){
        connectToDb();

        boolean sucesso;
        String sql = "INSERT INTO livro ( tituloObra,autor_idautor,generoLiterario_idgeneroLiterario,listaInteresse_idlistaInteresse) VALUES ( ?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, livro.getTituloObra());
            pst.setInt(2, livro.getAutor_idautor());
            pst.setInt(3, livro.getGeneroLiterario_idgeneroLiterario());
            pst.setInt(4, livro.getListaInteresse_idlistaInteresse());
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

    public boolean updateLivro(int id, Livro livro){
        connectToDb();

        boolean sucesso;
        String sql = "UPDATE livro SET tituloObra = ?, autor_idautor = ?, generoLiterario_idgeneroLiterario = ?,listaInteresse_idlistaInteresse WHERE id = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, livro.getTituloObra());
            pst.setInt(2, livro.getAutor_idautor());
            pst.setInt(3, livro.getGeneroLiterario_idgeneroLiterario());
            pst.setInt(4, livro.getListaInteresse_idlistaInteresse());
            pst.setInt(5, id);
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

    public boolean deleteLivro(int id){
        connectToDb();

        boolean sucesso;
        String sql = "DELETE FROM livro WHERE idLivro = ?";
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

    public ArrayList<Livro> selectLivro() {
        connectToDb();

        ArrayList<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de livros:");
            while (rs.next()) {
                Livro livroAux = new Livro(
                        rs.getInt("idLivro"), rs.getString("tituloObra"),
                        rs.getInt("autor_idautor"),rs.getInt("generoLiterario_idgeneroLiterario"),
                        rs.getInt("listaInteresse_idlistaInteresse"));
                System.out.println("ID: " + livroAux.getIdLivro() + " Título: " + livroAux.getTituloObra() +
                        " Autor: " + livroAux.getAutor_idautor() + " Gênero literário: " + livroAux.getGeneroLiterario_idgeneroLiterario() +
                        " Lista de interesse: " + livroAux.getListaInteresse_idlistaInteresse());
                System.out.println("--------------------");
                livros.add(livroAux);
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
        return livros;
    }
    public Livro selectUnicoLivro(int id){
        connectToDb();
        Livro livroAux = null;
        boolean sucesso;
        String sql = "SELECT * FROM livro WHERE idLivro = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            sucesso = true;

            System.out.println("Dados da editora:");
            while (rs.next()) {
                livroAux = new Livro(
                        rs.getInt("idLivro"), rs.getString("tituloObra"),
                        rs.getInt("autor_idautor"),rs.getInt("generoLiterario_idgeneroLiterario"),
                        rs.getInt("listaInteresse_idlistaInteresse"));
                System.out.println("ID: " + livroAux.getIdLivro() + " Título: " + livroAux.getTituloObra() +
                        " Autor: " + livroAux.getAutor_idautor() + " Gênero literário: " + livroAux.getGeneroLiterario_idgeneroLiterario() +
                        " Lista de interesse: " + livroAux.getListaInteresse_idlistaInteresse());
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
        return livroAux;
    }
    public void joinAutorObraEditora(){
        connectToDb();
        String sql = "SELECT a.nomeAutor, l.tituloObra, e.nomeEditora FROM autor AS a JOIN livro AS l ON l.autor_idautor = a.idautor JOIN Livro_has_Editora AS lhe ON lhe.Livro_idLivro = l.idLivro JOIN editora AS e ON lhe.editora_ideditora = e.ideditora ORDER BY a.nomeAutor";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while(rs.next()) {
                System.out.println("Autor: " + rs.getString("nomeAutor"));
                System.out.println("Título: " + rs.getString("tituloObra"));
                System.out.println("Editora: " + rs.getString("nomeEditora"));
                System.out.println("---------------------------");
            }
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());

        } finally {
            try {
                con.close();
                pst.close();
                rs.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
    }
    public void joinMembroResenhaLivro(){
        connectToDb();
        String sql ="SELECT m.nomeMembro, r.nota, l.tituloObra FROM membro AS m JOIN resenha AS r ON m.idmembro = r.membro_idmembro JOIN livro AS l ON r.Livro_idLivro = l.idLivro ORDER BY l.tituloObra";
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while(rs.next()) {
                System.out.println("Membro: " + rs.getString("nomeMembro"));
                System.out.println("Nota: " + rs.getString("nota"));
                System.out.println("Livro: " + rs.getString("tituloObra"));
                System.out.println("---------------------------");
            }
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());

        } finally {
            try {
                con.close();
                pst.close();
                rs.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
    }
    public void joinMembroEmprestimoLivro(){
        connectToDb();
        String sql ="select m.nomeMembro, e.dataDevolucao, l.tituloObra from membro as m\n" +
                "join emprestimos as e on m.idmembro = e.membro_idmembro\n" +
                "join copiaLivro as c on e.copiaLivro_idcopiaLivro = c.idcopiaLivro \n" +
                "join livro as l on c.idcopiaLivro = l.idLivro\n" +
                "where c.CopiaDisponivel = \"Nao\"\n" +
                "order by m.nomeMembro";
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while(rs.next()) {
                System.out.println("Membro: " + rs.getString("nomeMembro"));
                System.out.println("Data de Devolucao: " + rs.getString("dataDevolucao"));
                System.out.println("Livro: " + rs.getString("tituloObra"));
                System.out.println("---------------------------");
            }
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());

        } finally {
            try {
                con.close();
                pst.close();
                rs.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }

    }

}
