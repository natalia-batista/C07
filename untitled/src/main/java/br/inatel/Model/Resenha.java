package br.inatel.Model;

public class Resenha {
    int nota;
    String comentario;
    int membro_idmembro;
    int Livro_idLivro;

    public Resenha(int nota, String comentario, int membro_idmembro, int livro_idLivro) {
        this.nota = nota;
        this.comentario = comentario;
        this.membro_idmembro = membro_idmembro;
        Livro_idLivro = livro_idLivro;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public int getMembro_idmembro() {
        return membro_idmembro;
    }

    public int getLivro_idLivro() {
        return Livro_idLivro;
    }
}
