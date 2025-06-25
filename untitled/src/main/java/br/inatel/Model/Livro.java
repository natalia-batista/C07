package br.inatel.Model;

public class Livro {
    int idLivro;
    String tituloObra;
    int autor_idautor;
    int generoLiterario_idgeneroLiterario;
    int listaInteresse_idlistaInteresse;

    public Livro(int idLivro, String tituloObra, int autor_idautor, int generoLiterario_idgeneroLiterario, int listaInteresse_idlistaInteresse) {
        this.idLivro = idLivro;
        this.tituloObra = tituloObra;
        this.autor_idautor = autor_idautor;
        this.generoLiterario_idgeneroLiterario = generoLiterario_idgeneroLiterario;
        this.listaInteresse_idlistaInteresse = listaInteresse_idlistaInteresse;
    }

    public Livro(String tituloObra, int autor_idautor, int generoLiterario_idgeneroLiterario, int listaInteresse_idlistaInteresse) {
        this.tituloObra = tituloObra;
        this.autor_idautor = autor_idautor;
        this.generoLiterario_idgeneroLiterario = generoLiterario_idgeneroLiterario;
        this.listaInteresse_idlistaInteresse = listaInteresse_idlistaInteresse;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public String getTituloObra() {
        return tituloObra;
    }

    public int getAutor_idautor() {
        return autor_idautor;
    }

    public int getGeneroLiterario_idgeneroLiterario() {
        return generoLiterario_idgeneroLiterario;
    }

    public int getListaInteresse_idlistaInteresse() {
        return listaInteresse_idlistaInteresse;
    }
}
