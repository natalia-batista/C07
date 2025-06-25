package br.inatel.Model;

public class Livro_has_editora {
    int Livro_idLivro;
    int editora_ideditora;

    public Livro_has_editora(int livro_idLivro, int editora_ideditora) {
        Livro_idLivro = livro_idLivro;
        this.editora_ideditora = editora_ideditora;
    }

    public int getLivro_idLivro() {
        return Livro_idLivro;
    }

    public int getEditora_ideditora() {
        return editora_ideditora;
    }
}
