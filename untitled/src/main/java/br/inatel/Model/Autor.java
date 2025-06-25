package br.inatel.Model;

public class Autor {
    int idautor;
    String nomeAutor;
    String nacionalidadeAutor;

    public Autor(int idautor, String nomeAutor, String nacionalidadeAutor) {
        this.idautor = idautor;
        this.nomeAutor = nomeAutor;
        this.nacionalidadeAutor = nacionalidadeAutor;
    }

    public Autor(String nomeAutor, String nacionalidadeAutor) {
        this.nomeAutor = nomeAutor;
        this.nacionalidadeAutor = nacionalidadeAutor;
    }

    public int getIdautor() {
        return idautor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public String getNacionalidadeAutor() {
        return nacionalidadeAutor;
    }
}
