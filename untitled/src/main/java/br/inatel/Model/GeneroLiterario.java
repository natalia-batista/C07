package br.inatel.Model;

public class GeneroLiterario {
    int idgeneroLiterario;
    String genero;

    public GeneroLiterario(int idgeneroLiterario, String genero) {
        this.idgeneroLiterario = idgeneroLiterario;
        this.genero = genero;
    }

    public GeneroLiterario(String genero) {
        this.genero = genero;
    }

    public int getIdgeneroLiterario() {
        return idgeneroLiterario;
    }

    public String getGenero() {
        return genero;
    }
}
